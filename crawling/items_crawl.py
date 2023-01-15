import random

import requests
import time
import pandas as pd
from datetime import datetime
from bs4 import BeautifulSoup


def to_csv(conv_items_info):
    conv_data = pd.DataFrame(conv_items_info)
    conv_data.columns = ['brand_name', 'category', 'item_name', 'price', 'discount', 'event', 'event_month', 'image']
    conv_data.to_csv('conv_items.csv', encoding='utf-8-sig')



# 전체 편의점
url = "https://pyony.com/search/"
webpage = requests.get(url, verify=False)  # https 인증 회피
soup = BeautifulSoup(webpage.content, "html.parser")

# 마지막 페이지 추출
page = soup.select(".page-link")
last_page = page[len(page) - 1].get('href')
_last_page = last_page.replace("?page=", "")

# print("last page", _last_page)

max_page = int(_last_page)
page_items_num = 20

items_info = []

# 할인 하는 달
now = datetime.now()

for x in range(1, max_page + 1):
    page_url = url + "?page=" + str(x)
    webpage = requests.get(page_url, verify=False)
    soup = BeautifulSoup(webpage.content, "html.parser")

    for j in range(0, len(soup.select(".card-body")) - 3):
        # 브랜드명, 카테고리
        item_metas = soup.select(".card-header")[j].get_text().split()[:2]
        if item_metas[0] == 'C·SPACE' or item_metas[0] == 'MINISTOP':
            continue
        # 상품명, 가격, 할인 가격, 이벤트 종류
        item_details = soup.select(".card-body")[j].get_text().replace(" ", "").split()[:4]

        # 이미지 url
        img = soup.select(".prod_img")[j].get('src')
        # print(img)
        # print(item_metas)
        item_info = []

        for meta in item_metas:
            if meta:
                item_info.append(meta)
        for detail in item_details:
            if detail:
                item_info.append(detail)
        item_info.append(now.strftime('%Y-%m'))
        item_info.append(img)
        items_info.append(item_info)
    # print(item_info)
    time.sleep(random.uniform(0.3, 1))
    print(len(items_info))

to_csv(items_info)
