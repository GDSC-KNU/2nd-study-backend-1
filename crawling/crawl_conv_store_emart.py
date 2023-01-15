import random
import re
import time
import pandas as pd
import requests

from bs4 import BeautifulSoup

def to_csv(conv_stores_info):
    conv_data = pd.DataFrame(conv_stores_info)
    conv_data.columns = ['brand_name', 'branch_name', 'address', 'latitude', 'longitude', 'service']
    conv_data.to_csv('emart_conv_stores.csv', encoding='utf-8-sig')

conv_store_url = 'https://emart24.co.kr/introduce2/findBranch.asp'
conv_store_end_button = '//*[@id="skipCont"]/div[2]/div[3]/div/a[14]'

conv_stores_info = []


data = {'wpsido': '', 'spgugun': '', 'cpage': '1', 'service_cv': '', 'stplacesido': '',
        'stplacegugun': '', 'sText': ''}

emart_service = ['24시간', '원두커피', '택배', '토토', 'ATM', '스무디킹', '애플악세사리', '앱택배', '와인', 'QR출입']


webpage = requests.get(conv_store_url, data=data)
soup = BeautifulSoup(webpage.content, 'html.parser')
end_page = int(re.findall('\(([^)]+)', soup.select(".paging > a")[-1].get('href'))[0].replace("'", ""))

for i in range(end_page):
    data['cpage'] = i + 1
    webpage = requests.get(conv_store_url, data=data)
    soup = BeautifulSoup(webpage.content, 'html.parser')
    conv_list = soup.select("#skipCont > div.section > div.find_listArea.openList > table > tbody > tr")
    # print(end_page)
    # print(conv_list[0].text)
    for k in range(len(conv_list)):
        conv_store_info = ['emart24']
        conv_name = conv_list[k].select_one('strong').text
        conv_address = conv_list[k].select_one('p').text.split("|")[0].replace(u'\xa0', u' ').strip()
        conv_coordinate = conv_list[k].select_one('.btnDgray').get('href')[20:-2].replace("\'", "").split(',')[-2:]
        conv_latitude = conv_coordinate[1].strip()
        if conv_latitude == '':
            conv_latitude = '-1'
        conv_longitude = conv_coordinate[0].strip()
        if conv_longitude == '':
            conv_longitude = '-1'
        conv_service_list = conv_list[k].select('.find_listSelect_Img > img')
        service_list = []
        for inx, service in enumerate(conv_service_list):
            if '_on' in service.get('src'):
                service_list.append(emart_service[inx])
        conv_service = ", ".join(service_list)
        conv_store_info.append(conv_name)
        conv_store_info.append(conv_address)
        conv_store_info.append(conv_latitude)
        conv_store_info.append(conv_longitude)
        conv_store_info.append(conv_service)

        # print(conv_store_info)
        conv_stores_info.append(conv_store_info)
    time.sleep(random.uniform(0.3, 1))
    print(i, len(conv_stores_info))

to_csv(conv_stores_info)
