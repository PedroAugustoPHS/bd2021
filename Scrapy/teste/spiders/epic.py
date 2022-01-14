import scrapy
from scrapy.spiders import Spider
from teste.prices import myPrice
class EpicCrawlSpider(Spider):
    name = 'epicCrawl'
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
        'https://www.epicgames.com/store/pt-BR/p/overcooked',
        'https://www.epicgames.com/store/pt-BR/p/overcooked-2',
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