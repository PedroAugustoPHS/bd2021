import scrapy
from scrapy.linkextractors import LinkExtractor
from scrapy.spiders import CrawlSpider, Rule
from teste.prices import myPrice
class GogCrawlSpider(CrawlSpider):
    name = 'gogCrawl'
    allowed_domains = ['gog.com']
    start_urls = ['https://www.gog.com/games']

    rule_link = LinkExtractor(restrict_css='.ng-hide > .product-tile > a.product-tile__content')
    rule_preco_gog = Rule(rule_link , callback='parse_item', follow=False)
    rules = (
        rule_preco_gog,
    )

    def parse_item(self, response):
        item = myPrice()
        item['titulo'] = response.css('.productcard-basics__title::text').extract()
        item['preco'] = response.css('.product-actions-price__final-amount::text').extract()
        item['preco_sem_desconto'] = response.css('.product-actions-price__base-amount::text').extract()
        item['desconto'] = (response.css('.product-actions-price__discount').extract()
            or '0'
        )

        # O site bloqueia e proibe o crawler
        yield item