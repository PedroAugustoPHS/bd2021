import scrapy
from scrapy.linkextractors import LinkExtractor
from scrapy.spiders import CrawlSpider, Rule
from teste.items import myItem


class JogoCrawlSpider(CrawlSpider):
    name = 'jogoCrawl'
    allowed_domains = ['epicgames.com']
    start_urls = ['https://www.epicgames.com/store/pt-BR/collection/top-sellers']

    jogo_nome = LinkExtractor(restrict_css='.css-cnqlhg > .css-lrwy1y > div > div > div > a')
    rule_jogo_nome = Rule(jogo_nome, callback='parse_item', follow=False)
    rules = (
        rule_jogo_nome,
    )

    def parse_item(self, response):
        item = myItem()
        item['titulo'] =  response.css('.css-1p6kk8h::text').extract()
        item['desenvolvedora'] = response.xpath('//div[@class="css-10mlqmn"][1]/div[@class="css-fxdlmq"]/div[@class="css-btns76"]/span/text()').extract()
        item['publicadora'] = response.xpath('//div[@class="css-10mlqmn"][2]/div[@class="css-fxdlmq"]/div[@class="css-btns76"]/span/text()').extract()
        item['ano_publicacao'] = response.xpath('//div[@class="css-10mlqmn"][3]/div[@class="css-fxdlmq"]/div[@class="css-btns76"]/span/time/text()').extract()
        
        #pegando apenas a 1° categoria
        item['categoria'] = response.xpath('//div[@class="css-1kg0r30"][1]/div[@class="css-encdnt"]/div[@class="css-1pj7rfu"][2]/ul[@class="css-vs1xw0"]/li[@class="css-t8k7"][1]/a[@class="css-1672chc"]/span[@class="css-z3vg5b"]/span/text()').extract()
        item['img_src'] = response.xpath('//div[@class="css-u0wq1j"]/div/img/@src').extract_first()
        
        #mto grande
        item['descricao'] = response.xpath('//div[@id="about-long-description"]/span[@class="css-z3vg5b"]/div[@class="css-1lwib6p"]/div[@class="css-1chn1kq"]/text()').extract()
        
        item['gpu'] = (response.xpath('//div[@class="css-2sc5lq"][1]/span[.="Placa de vídeo"]/following-sibling::span/text()').extract()
            or response.xpath('//div[@class="css-2sc5lq"][1]/span[.="Placa de vídeo para Windows"]/following-sibling::span/text()').extract()
            or response.xpath('//div[@class="css-2sc5lq"][1]/span[.="Graphics"]/following-sibling::span/text()').extract()
            or 'não especificado'
        )

        item['so'] = (response.xpath('//div[@class="css-2sc5lq"][1]/span[contains(text(),"Sistema")]/following-sibling::span/text()').extract()
            or response.xpath('//div[@class="css-2sc5lq"][1]/span[.="OS"]/following-sibling::span/text()').extract()
            or 'não especificado'
        )

        item['cpu'] = (response.xpath('//div[@class="css-2sc5lq"][1]/span[contains(text(),"Process")]/following-sibling::span/text()').extract()
            or 'não especificado'
        )
        item['memoria_ram'] = (response.xpath('//div[@class="css-2sc5lq"][1]/span[contains(text(),"ram")]/following-sibling::span/text()').extract()
            or response.xpath('//div[@class="css-2sc5lq"][1]/span[contains(text(),"RAM")]/following-sibling::span/text()').extract()
            or response.xpath('//div[@class="css-2sc5lq"][1]/span[contains(text(),"Memória")]/following-sibling::span/text()').extract()
            or response.xpath('//div[@class="css-2sc5lq"][1]/span[contains(text(),"Memory")]/following-sibling::span/text()').extract()
            or 'não especificado'
        )
        item['armazenamento'] = (response.xpath('//div[@class="css-2sc5lq"][1]/span[contains(text(),"HDD")]/following-sibling::span/text()').extract()
            or response.xpath('//div[@class="css-2sc5lq"][1]/span[contains(text(),"Storage")]/following-sibling::span/text()').extract()
            or response.xpath('//div[@class="css-2sc5lq"][1]/span[contains(text(),"Armazenamento")]/following-sibling::span/text()').extract()
            or response.xpath('//div[@class="css-2sc5lq"][1]/span[contains(text(),"Disc")]/following-sibling::span/text()').extract()
            or response.xpath('//div[@class="css-2sc5lq"][1]/span[contains(text(),"disc")]/following-sibling::span/text()').extract()
            or'não especificado'
        )
        yield item