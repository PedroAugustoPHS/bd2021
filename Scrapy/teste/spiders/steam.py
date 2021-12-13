import scrapy
from scrapy.linkextractors import LinkExtractor
from scrapy.spiders import CrawlSpider, Rule
from teste.prices import myPrice
class SteamCrawlSpider(CrawlSpider):
    name = 'steamCrawl'
    allowed_domains = ['store.steampowered.com']
    start_urls = ['https://store.steampowered.com/']

    rule_link = LinkExtractor(restrict_css ='#store_search_link')

    rule_preco_steam = Rule(rule_link , callback='parse_item', follow=False)
    rules = (
        rule_preco_steam,
    )

    def parse_item(self, response):
        for price in response.xpath('//a[@class="search_result_row ds_collapse_flag app_impression_tracked"]'):
            item = myPrice()
            item['titulo'] = price.xpath('//span[@class="title"]/text').extract()
            item['preco_normal'] = price.xpath('//div[@class="col search_price  responsive_secondrow"]/text').extract()
            item['preco_sem_desconto'] = price.xpath('//div[@class="col search_price discounted responsive_secondrow"]/span/strike/text').extract()
            item['preco_com_desconto'] = price.xpath('//div[@class="col search_price discounted responsive_secondrow"]/text').extract()
            item['desconto'] = price.xpath('//div[@class="col search_discount responsive_secondrow"]/span/text').extract()
            yield item