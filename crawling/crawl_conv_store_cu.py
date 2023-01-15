import random
import re
import time
import pandas as pd
import requests

from bs4 import BeautifulSoup

def to_csv(conv_stores_info):
    conv_data = pd.DataFrame(conv_stores_info)
    conv_data.columns = ['brand_name', 'branch_name', 'address', 'latitude', 'longitude', 'service']
    conv_data.to_csv('cu_conv_stores.csv', encoding='utf-8-sig')


conv_store_base_url = 'https://cu.bgfretail.com/store/list_Ajax.do?pageIndex='
conv_store_parameter_url = '&listType=&jumpoCode=&jumpoLotto=&jumpoToto=&jumpoCash=&jumpoHour=&jumpoCafe=&' \
                 'jumpoDelivery=&jumpoBakery=&jumpoFry=&jumpoAdderss=&jumpoSido=&' \
                 'jumpoGugun=&jumpodong=&user_id=&sido=&Gugun=&jumpoName=#'


conv_stores_info = []
cookie = {
    'Host': 'cu.bgfretail.com',
    'Connection': 'keep-alive',
    'Content-Length': '207',
    'Accept': 'text/html, */*; q=0.01',
    'Origin': 'http://cu.bgfretail.com',
    'X-Requested-With': 'XMLHttpRequest',
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36',
    'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
    'Referer': 'http://cu.bgfretail.com/store/list.do?category=store'
}

cu_service = ['24시간', '택배', '베이커리', '튀김', '에스프레소', '로또', '토토', '현금지급기', '무인복합기', 'POS현금인출', '공유보조 배터리']

conv_store_page = 1

while True:
    conv_store_url = conv_store_base_url + str(conv_store_page) + conv_store_parameter_url
    webpage = requests.get(conv_store_url, cookies=cookie)
    soup = BeautifulSoup(webpage.content, "html.parser")
    cu_stores = soup.select("tr")
    if len(cu_stores) < 2:
        break
    for k in range(1, len(cu_stores)):
        conv_store_info = ['cu']
        conv_store_name = cu_stores[k].select_one(".name").text
        conv_store_address = cu_stores[k].select_one("address").text
        conv_store_service_list = cu_stores[k].select(".detail_info > ul > li")
        service_list = []
        for inx, service in enumerate(conv_store_service_list):
            if len(service['class']) > 1:
                service_list.append(cu_service[inx])
        conv_service = ", ".join(service_list)
        conv_store_info.append(conv_store_name)
        conv_store_info.append(conv_store_address)
        # 좌표 없음...
        conv_store_info.append(-1)
        conv_store_info.append(-1)
        conv_store_info.append(conv_service)
        # print(conv_service)
        # print(conv_store_info)
        conv_stores_info.append(conv_store_info)
    print(len(conv_stores_info))
    conv_store_page += 1
    time.sleep(random.uniform(0.3, 1))

to_csv(conv_stores_info)
