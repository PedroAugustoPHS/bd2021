# Define here the models for your scraped items
#
# See documentation in:
# https://docs.scrapy.org/en/latest/topics/items.html

import scrapy


class myItem(scrapy.Item):
    titulo = scrapy.Field()
    desenvolvedora = scrapy.Field()
    publicadora = scrapy.Field()
    ano_publicacao = scrapy.Field()
    categoria = scrapy.Field()
    img_src = scrapy.Field()
    descricao = scrapy.Field()
    so = scrapy.Field()
    cpu = scrapy.Field()
    memoria_ram = scrapy.Field()
    armazenamento = scrapy.Field()
    gpu = scrapy.Field()
    pass
