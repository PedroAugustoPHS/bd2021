import scrapy
from scrapy.linkextractors import LinkExtractor
from scrapy.spiders import CrawlSpider, Rule
from teste.prices import myPrice
class SteamCrawlSpider(CrawlSpider):
    name = 'steamCrawl'
    allowed_domains = ['store.steampowered.com']
    start_urls = ['https://store.steampowered.com/search/?term=ultrakill']

    rule_link = LinkExtractor()

    rule_preco_steam = Rule(rule_link , callback='parse_item', follow=False)
    rules = (
        rule_preco_steam,
    )

    def parse_item(self, response):
        item = myPrice()
        item['titulo'] = response.xpath('//div[@id="appHubAppName"]/text').extract()
        item['preco'] = response.xpath('//div[@class="game_purchase_price price"]/text').extract()
        #item['preco_sem_desconto'] = response.xpath('//div[@class="col search_price discounted responsive_secondrow"]/span/strike/text').extract()
        #item['desconto'] = response.xpath('//div[@class="col search_discount responsive_secondrow"]/span/text').extract()
        yield item