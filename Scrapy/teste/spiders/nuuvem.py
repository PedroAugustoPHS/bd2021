import scrapy
from scrapy.linkextractors import LinkExtractor
from scrapy.spiders import CrawlSpider, Rule
from teste.prices import myPrice
class NuuvemCrawlSpider(CrawlSpider):
    name = 'nuuvemCrawl'
    allowed_domains = ['www.nuuvem.com']
    start_urls = ['https://www.nuuvem.com/catalog/platforms/pc/sort/title/sort-mode/asc']

    preco_nuuvem = LinkExtractor()
    rule_preco_nuuvem = Rule(preco_nuuvem, callback='parse_item', follow=False)
    rules = (
        rule_preco_nuuvem,
    )

    def parse_item(self, response):
        item = myPrice()
        item['titulo'] = response.xpath('//h3[@class="product-title double-line-name"]')
        item['preco'] = response.xpath('//span[@class="product-price--val"]')
        item['desconto'] = response.xpath('//span[@class="product-price--discount"]')
    
        yield item