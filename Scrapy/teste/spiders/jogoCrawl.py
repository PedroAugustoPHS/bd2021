import scrapy
from scrapy.spiders import Spider
from teste.items import myItem


class JogoCrawlSpider(Spider):
    name = 'jogoCrawl'
    allowed_domains = ['epicgames.com']
    start_urls = [
        'https://www.epicgames.com/store/pt-BR/p/my-time-at-portia',
        'https://www.epicgames.com/store/pt-BR/p/red-dead-redemption-2',
        'https://www.epicgames.com/store/pt-BR/p/lego-builders-journey',
        'https://www.epicgames.com/store/pt-BR/p/songs-for-a-hero-definitive-edition-cd4dce',
        'https://www.epicgames.com/store/pt-BR/p/among-us',
        'https://www.epicgames.com/store/pt-BR/p/while-true-learn',
        'https://www.epicgames.com/store/pt-BR/p/death-stranding',
        'https://www.epicgames.com/store/pt-BR/p/farming-simulator-22',
        'https://www.epicgames.com/store/pt-BR/p/mafia-ii-definitive-edition',
        'https://www.epicgames.com/store/pt-BR/p/overcooked-2',
        'https://www.epicgames.com/store/pt-BR/p/overcooked',
        'https://www.epicgames.com/store/pt-BR/p/never-alone-kisima-ingitchuna',
        'https://www.epicgames.com/store/pt-BR/p/pc-building-simulator',
        'https://www.epicgames.com/store/pt-BR/p/eastward',
        'https://www.epicgames.com/store/pt-BR/p/lego-batman',
        'https://www.epicgames.com/store/pt-BR/p/lego-batman-2',
        'https://www.epicgames.com/store/pt-BR/p/lego-batman-3',
        'https://www.epicgames.com/store/pt-BR/p/vampyr',
        'https://www.epicgames.com/store/pt-BR/p/darkest-dungeon',
        'https://www.epicgames.com/store/pt-BR/p/enter-the-gungeon',
        'https://www.epicgames.com/store/pt-BR/p/minit',
        'https://www.epicgames.com/store/pt-BR/p/a-plague-tale-innocence',
        'https://www.epicgames.com/store/pt-BR/p/south-park-the-stick-of-truth',
        'https://www.epicgames.com/store/pt-BR/p/chivalry-2',
        'https://www.epicgames.com/store/pt-BR/p/superhot',
        'https://www.epicgames.com/store/pt-BR/p/far-cry',
        'https://www.epicgames.com/store/pt-BR/p/far-cry-3',
        'https://www.epicgames.com/store/pt-BR/p/far-cry-4',
        'https://www.epicgames.com/store/pt-BR/p/far-cry-5',
        'https://www.epicgames.com/store/pt-BR/p/just-die-already'
    ]

    def parse(self, response):
        item = myItem()
        item['titulo'] =  response.css('.css-1p6kk8h::text').extract() or response.css('.css-j00jcq::text').extract()
        item['desenvolvedora'] = response.xpath('//div[@class="css-10mlqmn"][1]/div[@class="css-fxdlmq"]/div[@class="css-btns76"]/span/text()').extract() or response.xpath('//div[@class="css-1k1wbhe"][1]/div[@class="css-fxdlmq"]/div[@class="css-btns76"]/span/text()').extract()
        item['publicadora'] = response.xpath('//div[@class="css-10mlqmn"][2]/div[@class="css-fxdlmq"]/div[@class="css-btns76"]/span/text()').extract() or response.xpath('//div[@class="css-1k1wbhe"][2]/div[@class="css-fxdlmq"]/div[@class="css-btns76"]/span/text()').extract()
        item['ano_publicacao'] = response.xpath('//div[@class="css-10mlqmn"][3]/div[@class="css-fxdlmq"]/div[@class="css-btns76"]/span/time/text()').extract() or response.xpath('//div[@class="css-1k1wbhe"][3]/div[@class="css-fxdlmq"]/div[@class="css-btns76"]/span/time/text()').extract()
        
        #pegando apenas a 1° categoria
        item['categoria'] = response.xpath('//div[@class="css-1kg0r30"][1]/div[@class="css-encdnt"]/div[@class="css-1pj7rfu"][2]/ul[@class="css-vs1xw0"]/li[@class="css-t8k7"][1]/a[@class="css-1672chc"]/span[@class="css-z3vg5b"]/span/text()').extract() or response.xpath('//div[@class="css-1kg0r30"][1]/div[@class="css-encdnt"]/div[@class="css-1pj7rfu"][2]/ul[@class="css-vs1xw0"]/li[@class="css-t8k7"][1]/a[@class="css-1672chc"]/span[@class="css-vs1xw0"]/span/text()').extract()
        item['img_src'] = response.xpath('//div[@class="css-u0wq1j"]/div/img/@src').extract_first()
        
        #mto grande
        item['descricao'] = response.xpath('//div[@id="about-long-description"]/span[@class="css-z3vg5b"]/div[@class="css-1lwib6p"]/div[@class="css-1chn1kq"]/text()').extract() or response.xpath('//div[@class="css-1g0mw3g"][1]/text()').extract()
        
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