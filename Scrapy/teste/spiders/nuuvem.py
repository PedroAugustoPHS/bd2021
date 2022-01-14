import scrapy
from scrapy.spiders import Spider
from teste.prices import myPrice
class NuuvemCrawlSpider(Spider):
    name = 'nuuvemCrawl'
    allowed_domains = ['www.nuuvem.com']
    start_urls = [
        'https://www.nuuvem.com/item/my-time-at-portia',
        'https://www.nuuvem.com/item/red-dead-redemption-2',
        'https://www.nuuvem.com/item/lenda-do-heroi-edicao-definitiva',
        'https://www.nuuvem.com/item/death-stranding',
        'https://www.nuuvem.com/item/farming-simulator-22',
        'https://www.nuuvem.com/item/mafia-2-definitive-edition',
        'https://www.nuuvem.com/item/overcooked',
        'https://www.nuuvem.com/item/overcooked-2',
        'https://www.nuuvem.com/item/never-alone-kisima-ingitchuna',
        'https://www.nuuvem.com/item/pc-building-simulator',
        'https://www.nuuvem.com/item/eastward',
        'https://www.nuuvem.com/item/lego-batman',
        'https://www.nuuvem.com/item/lego-batman-2-dc-super-heroes',
        'https://www.nuuvem.com/item/lego-batman-3-beyond-gotham',
        'https://www.nuuvem.com/item/vampyr',
        'https://www.nuuvem.com/item/darkest-dungeon',
        'https://www.nuuvem.com/item/enter-gungeon',
        'https://www.nuuvem.com/item/minit',
        'https://www.nuuvem.com/item/a-plague-tale-innocence',
        'https://www.nuuvem.com/item/south-park-the-stick-of-truth',
        'https://www.nuuvem.com/item/chivalry-2',
        'https://www.nuuvem.com/item/superhot',
        'https://www.nuuvem.com/item/far-cry',
        'https://www.nuuvem.com/item/far-cry-3',
        'https://www.nuuvem.com/item/far-cry-4',
        'https://www.nuuvem.com/item/far-cry-5',
        'https://www.nuuvem.com/item/just-die-already'
    ]

    def parse(self, response):
        item = myPrice()
        real: str = response.xpath('//span[@class="product-price--val"]/span[@class="integer"]/text()').extract_first()
        centavo: str = response.xpath('//span[@class="product-price--val"]/span[@class="decimal"]/text()').extract_first()
        preco = (real + centavo)
        item = myPrice()
        item['titulo'] = response.xpath('//h1[@class="product-title"]/span/text()').extract_first() or response.xpath('//h3[@class="product-title single-line-name"]/text()').extract_first()
        item['preco'] = preco
        item['preco_sem_desconto'] = response.xpath('//span[@class="product-price--val"]/span[@class="product-price--old"]/text()').extract_first()
        item['desconto'] = response.xpath('//span[@class="product-price--discount"]/text()').extract_first() or '0'
    
        yield item