import scrapy
from scrapy.spiders import Spider
from teste.prices import myPrice
from scrapy.http import FormRequest
import logging
logger = logging.getLogger(__name__)
class SteamCrawlSpider(Spider):
    name = 'steamCrawl'
    allowed_domains = ['store.steampowered.com']
    start_urls = ['https://store.steampowered.com/app/666140/My_Time_At_Portia/']

    def parse(self, response):
        if '/agecheck/app' in response.url:
            
            logger.debug(f"Button-type age check triggered for {response.url}.")
            
            form = response.css('#agegate_box form')
            action = form.xpath('@action').extract_first()
            name = form.xpath('input/@name').extract_first()
            value = form.xpath('input/@value').extract_first()
            
            formdata = {
            name: value,
            'ageDay': '1',
            'ageMonth': '1',
            'ageYear': '2000'
            }
            
            yield FormRequest(
            url=action,
            method='POST',
            formdata=formdata,
            callback=self.parse
            )
        else:
            item = myPrice()
            item['titulo'] = response.xpath('//div[@id="appHubAppName"]/text()').extract()
            item['preco'] = response.xpath('//div[@class="game_purchase_price price"]/text()').extract() or response.xpath('//div[@class="discount_block game_purchase_discount"]/div[@class="discount_prices"]/div[@class="discount_final_price"]/text()').extract()
            item['preco_sem_desconto'] = response.xpath('//div[@class="discount_block game_purchase_discount"]/div[@class="discount_prices"]/div[@class="discount_original_price"]/text()').extract() or "sem desconto"
            item['desconto'] = response.xpath('//div[@class="discount_block game_purchase_discount"]/div[@class="discount_pct"]/text()').extract() or "sem desconto"

            yield item
