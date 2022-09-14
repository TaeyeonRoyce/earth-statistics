import pandas as pd
import matplotlib.pyplot as plt
import statistics as st
import numpy as np

plt.rc('font', family='AppleGothic')
plt.rcParams['axes.unicode_minus'] = False

pathName = '../statistics/wine_info.xlsx'

df = pd.read_excel(pathName, engine='openpyxl')
priceList = df.iloc[4:672, 5].values.tolist()
rateList = df.iloc[4:672, 7].values.tolist()

yRange = np.arange(0, 5, 0.3)
plt.scatter(priceList, rateList)
plt.xlabel("와인 가격", fontsize=16)
plt.ylabel("평점", fontsize=12)
plt.yticks(yRange)

plt.show()
