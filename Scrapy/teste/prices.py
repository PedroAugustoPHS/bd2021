# Define here the models for your scraped prices items
#
# See documentation in:
# https://docs.scrapy.org/en/latest/topics/items.html

import scrapy

class myPrice(scrapy.Item):
    titulo = scrapy.Field()
    preco = scrapy.Field()
    preco_sem_desconto = scrapy.Field()
    desconto = scrapy.Field()
    pass
