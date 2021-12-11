import scrapy
from scrapy.linkextractors import LinkExtractor
from scrapy.spiders import CrawlSpider, Rule


class JogoCrawlSpider(CrawlSpider):
    name = 'jogoCrawl'
    allowed_domains = ['epicgames.com']
    start_urls = ['https://www.epicgames.com/store/pt-BR/browse?sortBy=title&sortDir=ASC&count=40']

    jogo_nome = LinkExtractor(restrict_css='.css-cnqlhg > .css-lrwy1y > div > div > div > a')
    rule_jogo_nome = Rule(jogo_nome, callback='parse_item', follow=False)
    rules = (
        rule_jogo_nome,
    )

    def parse_item(self, response):
        yield {
            'titulo': response.css('.css-1p6kk8h::text').get('').strip(),
            # 'desenvolvedora': response.xpath('//div[@class="dev_row"]/div[@class="summary"]/text()').get(),
            # 'categoria':response.xpath('//div[@class="popular_tags"]/a/text()').get(),
            #'descricao': response.xpath('//div[@class="game_description_snippet"]/text()').get().strip(),
            'ano_publicacao': response.css('time::text').get('').strip(),
            'img_src': response.xpath('//img[@class="css-5iu9l4"]/@src').get('').strip()
            # 'cpu': response.css('.bb_ul > li:nth-child(2)::text').get(),
            # 'so': response.css('.bb_ul > li:nth-child(3)::text').get(),
            # 'memoria_ram': response.css('.bb_ul > li:nth-child(4)::text').get(),
            # 'gpu': response.css('.bb_ul > li:nth-child(5)::text').get(),
            # 'armazenamento': response.css('.bb_ul > li:nth-child(8)::text').get(),
        }