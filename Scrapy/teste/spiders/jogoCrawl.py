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
            'desenvolvedora': response.xpath('//div[@class="css-10mlqmn"][1]/div[@class="css-fxdlmq"]/div[@class="css-btns76"]/span/text()').get(),
            'publicadora': response.xpath('//div[@class="css-10mlqmn"][2]/div[@class="css-fxdlmq"]/div[@class="css-btns76"]/span/text()').get(),
            'ano_publicacao': response.xpath('//div[@class="css-10mlqmn"][3]/div[@class="css-fxdlmq"]/div[@class="css-btns76"]/span/time/text()').get(),
            

            #pegando apenas a 1° categoria
            'categoria':response.xpath('//div[@class="css-1kg0r30"][1]/div[@class="css-encdnt"]/div[@class="css-1pj7rfu"][2]/ul[@class="css-vs1xw0"]/li[@class="css-t8k7"][1]/a[@class="css-1672chc"]/span[@class="css-z3vg5b"]/span/text()').get(),
            'img_src': response.xpath('//div[@class="css-u0wq1j"]/div/img/@src').get(''),

            # mto grande, mas funcionando
            #'descricao': response.xpath('//div[@id="about-long-description"]/span[@class="css-z3vg5b"]/div[@class="css-1lwib6p"]/div[@class="css-1chn1kq"]/text()').get(),

            #problema de ordem para cada jogo
            'so': response.xpath('//div[@class="css-3rds8q"][1]/div[@class="css-2sc5lq"][1]/span[@class="css-z3vg5b"]/text()').get(),
            'cpu': response.xpath('//div[@class="css-3rds8q"][2]/div[@class="css-2sc5lq"][1]/span[@class="css-z3vg5b"]/text()').get(),
            'memoria_ram': response.xpath('//div[@class="css-3rds8q"][3]/div[@class="css-2sc5lq"][1]/span[@class="css-z3vg5b"]/text()').get(),
            'armazenamento': response.xpath('//div[@class="css-3rds8q"][4]/div[@class="css-2sc5lq"][1]/span[@class="css-z3vg5b"]/text()').get(),
            'gpu': response.xpath('//div[@class="css-3rds8q"][6]/div[@class="css-2sc5lq"][1]/span[@class="css-z3vg5b"]/text()').get()
        }