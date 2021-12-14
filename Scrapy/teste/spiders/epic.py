import scrapy
from scrapy.linkextractors import LinkExtractor
from scrapy.spiders import CrawlSpider, Rule
from teste.prices import myPrice
class EpicCrawlSpider(CrawlSpider):
    name = 'epicCrawl'
    allowed_domains = ['epicgames.com']
    start_urls = ['https://www.epicgames.com/store/pt-BR/browse?sortBy=title&sortDir=ASC&count=50']

    preco_epic = LinkExtractor(restrict_css='.css-cnqlhg > .css-lrwy1y > div > div > div > a')
    rule_preco_epic = Rule(preco_epic, callback='parse_item', follow=False)
    rules = (
        rule_preco_epic,
    )

    def parse_item(self, response):
        item = myPrice()
        item['titulo'] =  response.css('.css-1p6kk8h::text').extract() or response.css('.css-j00jcq::text').extract()
        item['preco'] = (response.css('.css-l24hbj > .css-z3vg5b::text').extract()
            or response.xpath('//div[@class="css-l24hbj"][2]/span[@class="css-z3vg5b"]')
            or response.css('.css-l24hbj > .css-z3vg5b > span::text').extract()
            or response.css('.css-8en90x > span::text').extract()
            or 'não especificado')
        item['preco_sem_desconto'] = response.css('.css-11ksoqt > .css-1rcj98u::text').extract() or 'sem desconto'
        item['desconto'] = response.css('.css-15fdr99 > div::text').get() or '0'
        
        yield item