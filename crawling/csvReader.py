import scipy.io
import csv
import pymysql

conn = pymysql.connect(host='', user='',password='',db='conv', charset='utf8')

curs=conn.cursor()
conn.commit()

f=open('emart_conv_stores.csv', 'r', encoding='UTF8')
csvReader = csv.reader(f)

next(csvReader)
for row in csvReader:
    id = row[0]
    brand_name = row[1]
    branch_name = row[2]
    address = row[3]
    latitude = row[4]
    longitude = row[5]
    service = row[6]

    point = 'POINT({0} {1})'.format(longitude, latitude)

    sql = """insert into store (branch_name, brand_name, location, like_count) values (%s, %s, ST_PointFromText(%s), %s)"""
    curs.execute(sql, (branch_name, brand_name, point, 0))

    print(branch_name, point)

conn.commit()
f.close()
conn.close()