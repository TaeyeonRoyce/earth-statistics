import pandas as pd
import matplotlib.pyplot as plt
import statistics as st
import numpy as np


plt.rc('font', family='AppleGothic')
plt.rcParams['axes.unicode_minus'] = False

pathName = '../statistics/wine_info.xlsx'

df = pd.read_excel(pathName, engine='openpyxl')
priceList = df.iloc[4:661, 4].values.tolist()
priceMean = st.mean(priceList)
priceMedian = st.median(priceList)
priceStDeviation = st.stdev(priceList)


xRangeMin = 4000
xRangeMax = 50000
xRange = np.arange(xRangeMin, xRangeMax, 10000)

yRangeMin = 0
yRangeMax = 70

plt.subplot(4, 1, 1)
plt.suptitle("이마트 와인 가격 도수 분포표", fontsize=16)
plt.xlim(xRangeMin, xRangeMax)
plt.ylim(yRangeMin, yRangeMax)
plt.xticks(xRange)
plt.hist(priceList, bins=30, label='bins=30', rwidth=0.8)
plt.axvline(priceMean, color='r', linestyle='--',
            linewidth=1, label='average')
plt.axvline(priceMedian, color='black', linestyle='--',
            linewidth=1, label='median')
plt.legend()

plt.subplot(4, 1, 2)
plt.xlim(xRangeMin, xRangeMax)
plt.ylim(yRangeMin, yRangeMax)
plt.xticks(xRange)
plt.hist(priceList, bins=50, label='bins=50', color='orange', rwidth=0.8)
plt.axvline(priceMean, color='r', linestyle='--',
            linewidth=1, label='average')
plt.axvline(priceMedian, color='black', linestyle='--',
            linewidth=1, label='median')
plt.legend()

plt.subplot(4, 1, 3)
plt.xlim(xRangeMin, xRangeMax)
plt.ylim(yRangeMin, yRangeMax)
plt.xticks(xRange)
plt.hist(priceList, bins=80, label='bins=80', color='green', rwidth=0.8)
plt.axvline(priceMean, color='r', linestyle='--',
            linewidth=1, label='average')
plt.axvline(priceMedian, color='black', linestyle='--',
            linewidth=1, label='median')
plt.legend()

plt.subplot(4, 1, 4)
plt.xlim(xRangeMin, xRangeMax)
plt.ylim(yRangeMin, yRangeMax)
plt.xticks(xRange)
plt.hist(priceList, bins=30, label='bins=30', rwidth=0.8)
plt.hist(priceList, bins=50, label='bins=50', rwidth=0.8)
plt.hist(priceList, bins=80, label='bins=80', rwidth=0.8)
plt.axvline(priceMean, color='r', linestyle='--',
            linewidth=1, label='average')
plt.axvline(priceMedian, color='black', linestyle='--',
            linewidth=1, label='median')
plt.legend()


plt.show()
