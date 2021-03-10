import csv, re

def openCsv(fileName):             
    file = open(fileName, 'r')
    reader = csv.reader(file)
    output = []
    for data in reader:
        output.append(data)
    file.close()
    return output

def appendCsv(fileName, dataList):
    file = open(fileName, "a")
    fileObject = csv.writer(file, delimiter = ",")
    fileObject.writerows(dataList)
    file.close()

def writeCsv(fileName, dataList):   #입력물이 맞는지 확인할 필요
    file = open(fileName, 'w')
    fileObject = csv.writer(file, delimiter=',')
    fileObject.writerows(dataList)
    file.close()

def switch(listName):
    for subList in listName:
        for data in subList:
            if re.search(r'[^\d,.]', data):
                pass
            elif re.search(r'\d+', data):
                try:
                    subList[subList.index(data)] = float(re.sub(',', '', data))
                except:
                    pass
    return listName

