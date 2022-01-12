import scrapy
from scrapy.spiders import Spider
from teste.prices import myPrice
class NuuvemCrawlSpider(Spider):
    name = 'nuuvemCrawl'
    allowed_domains = ['www.nuuvem.com']
    start_urls = [
        'https://www.nuuvem.com/item/my-time-at-portia',
        "https://www.nuuvem.com/item/arms"
    ]

    def parse(self, response):
        item = myPrice()
        real: str = response.xpath('//span[@class="product-price--val"]/span[@class="integer"]/text()').extract_first()
        centavo: str = response.xpath('//span[@class="product-price--val"]/span[@class="decimal"]/text()').extract_first()
        preco = (real + centavo)
        item = myPrice()
        item['titulo'] = response.xpath('//h1[@class="product-title"]/span/text()').extract_first() or response.xpath('//h3[@class="product-title single-line-name"]/text()').extract_first()
        item['preco'] = preco
        item['preco_sem_desconto'] = response.xpath('//span[@class="product-price--val"]/span[@class="product-price--old"]/text()').extract_first()
        item['desconto'] = response.xpath('//span[@class="product-price--discount"]/text()').extract_first() or '0'
    
        yield item