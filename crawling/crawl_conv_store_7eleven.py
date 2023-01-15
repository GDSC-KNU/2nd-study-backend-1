import random
import re
import time
import pandas as pd
import requests

from bs4 import BeautifulSoup


def to_csv(conv_stores_info):
    conv_data = pd.DataFrame(conv_stores_info)
    conv_data.columns = ['brand_name', 'branch_name', 'address', 'latitude', 'longitude', 'service']
    conv_data.to_csv('7eleven_conv_stores.csv', encoding='utf-8-sig')


conv_store_url = 'https://www.7-eleven.co.kr/util/storeLayerPop.asp'
conv_store_parameter_url = ''

conv_store_page = 1

conv_stores_info = []

sido = ['강원도', '경기도', '경상남도', '경상북도', '광주', '대구', '대전', '부산', '서울', '세종',
        '울산', '인천', '전라남도', '전라북도', '제주도', '충청남도', '충청북도']

data = {'storeLaySido': '시/도', 'storeLayGu': '', 'hiddentext': 'none'}

for i in range(len(sido)):
    data['storeLaySido'] = sido[i]
    webpage = requests.get(conv_store_url, data=data)
    soup = BeautifulSoup(webpage.content, 'html.parser')
    conv_store_list = soup.select(".list_stroe > ul > li")
    for j in range(len(conv_store_list)):
        conv_store_info = ['7eleven']
        conv_name = conv_store_list[j].select_one('a > span').text.strip()
        conv_address = conv_store_list[j].select_one('a > span:nth-child(2)').text.replace(u'\xa0', u' ')
        conv_coordinate = re.findall('\(([^)]+)', conv_store_list[j].select_one('a').get('href'))[0].split(',')
        conv_latitude = conv_coordinate[1]
        conv_longitude = conv_coordinate[2]
        conv_service_list = conv_store_list[j].select('a > span > img')
        service_list = []
        for service in conv_service_list:
            service_list.append(service.get("alt"))
        conv_service = ", ".join(service_list)
        # print(conv_service)
        # print(conv_service_list)

        conv_store_info.append(conv_name)
        conv_store_info.append(conv_address)
        conv_store_info.append(conv_latitude)
        conv_store_info.append(conv_longitude)
        conv_store_info.append(conv_service)
        # print(conv_store_list.find_all(class_=re.compile('on')))
        # print(conv_address[0])
        conv_stores_info.append(conv_store_info)
        # print(conv_store_info)

    print(len(conv_stores_info))
    time.sleep(random.uniform(0.3, 1))

to_csv(conv_stores_info)
