import random
import time
import re
import pandas as pd

from webdriver_manager.chrome import ChromeDriverManager
from selenium import webdriver

def to_csv(conv_stores_info):
    conv_data = pd.DataFrame(conv_stores_info)
    conv_data.columns = ['brand_name', 'branch_name', 'address', 'latitude', 'longitude']
    conv_data.to_csv('gs25_conv_stores.csv', encoding='utf-8-sig')

conv_store_url = 'http://gs25.gsretail.com/gscvs/ko/store-services/locations'
conv_store_end_button = '//*[@id="pagingTagBox"]/a[4]'
conv_store_next_button = '//*[@id="pagingTagBox"]/a[3]'
conv_store_page = '//*[@id="pagingTag"]'
conv_stores_info = []

driver = webdriver.Chrome(ChromeDriverManager().install())
driver.get(conv_store_url)
end_button = driver.find_element_by_xpath(conv_store_end_button)
end_button.click()
time.sleep(2)
end_page = int(driver.find_element_by_xpath(conv_store_page).text.split("|")[-1])
driver.get(conv_store_url)
time.sleep(1)
for i in range(end_page):
    conv_list = driver.find_elements_by_xpath('//*[@id="storeInfoList"]/tr')
    # print(len(conv_list))
    for k in range(len(conv_list)):
        # print(conv_list[k])
        conv_store_info = ['gs25']
        conv_name = conv_list[k].find_element_by_class_name('st_name').text
        conv_address = conv_list[k].find_element_by_class_name('st_address').text
        conv_coordinate = re.findall('\(([^)]+)', conv_list[k].find_element_by_class_name('st_name')
                                     .get_attribute('href'))[0].split(',')
        conv_latitude = conv_coordinate[1].strip()
        if conv_latitude == '':
            conv_latitude = '-1'
        conv_longitude = conv_coordinate[2].strip()
        if conv_longitude == '':
            conv_longitude = '-1'
        conv_store_info.append(conv_name)
        conv_store_info.append(conv_address)
        conv_store_info.append(conv_latitude)
        conv_store_info.append(conv_longitude)
        conv_stores_info.append(conv_store_info)
        # print(conv_store_info)
    driver.find_element_by_xpath(conv_store_next_button).click()
    time.sleep(random.uniform(0.5, 1))
    print(i, len(conv_stores_info))

to_csv(conv_stores_info)
