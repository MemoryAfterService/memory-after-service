from collections import Counter
import csv
import numpy as np
from wordcloud import WordCloud
import matplotlib.pyplot as plt
from PIL import Image

data = []
f = open('./result/Data_Kakao_word.csv', 'r', encoding='utf-8-sig')
rea = list(csv.reader(f))
f.close
data = [rea[d][3] for d in range(len(rea))]
words = [i for i in data if len(i) > 1]

c = Counter(words)

tags = c.most_common(70)
mask = Image.new('RGBA', (256, 256), (255, 255, 255))
image = Image.open('cloud.png').convert('RGBA')
x, y = image.size
mask.paste(image, (0, 0, x, y), image)
mask = np.array(mask)
wc = WordCloud(font_path='HMKMRHD.TTF', width=256, height=256, scale=2, max_font_size=250, background_color ='white', colormap='Blues', mask=mask)
cloud = wc.generate_from_frequencies(dict(tags))

plt.figure()
plt.imshow(cloud)
wc.to_file('WordCloud.png')

# im = Image.open('cloud.png')
# mask_arr = np.array(im)
# wc = WordCloud(font_path='HMKMRHD.TTF', width=400, height=400, scale=1.5, max_font_size=250, background_color ='white', colormap='winter', mask=mask_arr).generate_from_frequencies(dic)
# gen = wc.generate_from_frequencies(c)
# plt.figure()
# plt.imshow(gen)
# wc.to_file('WordCloud.png')