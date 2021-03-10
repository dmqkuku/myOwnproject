import os, csv, re
import urllib.request as ur
from bs4 import BeautifulSoup as bs
from usecsv import writeCsv, openCsv, switch, appendCsv

harvard_health_data_url = 'https://www.health.harvard.edu/diet-and-weight-loss/calories-burned-in-30-minutes-of-leisure-and-routine-activities'

main_html = ur.urlopen(harvard_health_data_url)

harvard_health_data_soup = bs(main_html.read(), "html.parser")

# harvard_health_table = harvard_health_data_soup.select("main > div > div > article > div > div > table")
harvard_health_table = harvard_health_data_soup.find_all("table", {"width" : "624"})#옳은 방법인가?
# 아래 코드는 dummy file에 어펜드 하는 코드입니다.
# 사용후 dummy_file로 이동하여 Outdoor Activities를 검색하여 해당 항목 상단의 빈 점을 지워줍니다. 이렇게 하지 않으면 아래에서 처리할때 문제가 생깁니다.
# 어펜드 하는 코드이기 때문에, 한번 사용후 현재 코드들을 다시 사용하려면 comment_out시킵니다
# dummy_file_write = open("health_dummy.txt", "a")
#
#
# for elem in harvard_health_table:
#     harvard_health_table_tr = elem.find_all("tr")
#     for tr_elem in harvard_health_table_tr:
#         dummy_file_write.write(tr_elem.text)
# dummy_file_write.close()

dummy_file_read = open("health_dummy.txt", "r")

dummy_data_read = dummy_file_read.readlines()

# third attempt : 스트링은 immutable하기 때문에 원본을 바꾸지 않는다.

def process_n(str_elem):
    return str_elem.replace('\n', '')

new_n_processed_data = [process_n(str_elem) for str_elem in dummy_data_read]
new_empty_processed_data = [str_elem for str_elem in new_n_processed_data if str_elem]

# print(new_n_processed_data)
# print(new_empty_processed_data)


# Second attempt : 공식 docs에서는 map 사용을 피하라고 조언
# dummy_data_read = list(map(lambda str : str.strip(), dummy_data_read))

# First attempt
# while "\n" in dummy_data_read:
#     dummy_data_read.remove("\n")
# for elem in dummy_data_read:
#     elem.rstrip()
print(len(new_empty_processed_data)/4) #딱 나누어 떨어진다.

newly_four_groupby_data = [[new_empty_processed_data[x*4], new_empty_processed_data[x*4+1], new_empty_processed_data[x*4+2], new_empty_processed_data[x*4 + 3]] for x in range(0, int(len(new_empty_processed_data)/4))]
print(newly_four_groupby_data)

writeCsv("workout_crawling_data.csv", newly_four_groupby_data)





