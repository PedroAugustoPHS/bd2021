import scrapy
from scrapy.spiders import Spider
from teste.prices import myPrice
from scrapy.http import FormRequest
import logging
logger = logging.getLogger(__name__)
class SteamCrawlSpider(Spider):
    name = 'steamCrawl'
    allowed_domains = ['store.steampowered.com']
    start_urls = [
        'https://store.steampowered.com/app/666140/My_Time_At_Portia/',
        'https://store.steampowered.com/app/1174180/Red_Dead_Redemption_2/',
        'https://store.steampowered.com/app/1544360/LEGO_Builders_Journey/',
        'https://store.steampowered.com/app/389170/Songs_for_a_Hero__Definitive_Edition/',
        'https://store.steampowered.com/app/945360/Among_Us/',
        'https://store.steampowered.com/app/619150/while_True_learn/',
        'https://store.steampowered.com/app/1190460/DEATH_STRANDING/',
        'https://store.steampowered.com/app/1248130/Farming_Simulator_22/',
        'https://store.steampowered.com/app/1030830/Mafia_II_Definitive_Edition/',
        'https://store.steampowered.com/app/448510/Overcooked/',
        'https://store.steampowered.com/app/728880/Overcooked_2/',
        'https://store.steampowered.com/app/295790/Never_Alone_Kisima_Ingitchuna/',
        'https://store.steampowered.com/app/621060/PC_Building_Simulator/',
        'https://store.steampowered.com/app/977880/Eastward/',
        'https://store.steampowered.com/app/21000/LEGO_Batman_The_Videogame/',
        'https://store.steampowered.com/app/213330/LEGO_Batman_2_DC_Super_Heroes/',
        'https://store.steampowered.com/app/313690/LEGO_Batman_3_Beyond_Gotham/',
        'https://store.steampowered.com/app/427290/Vampyr/',
        'https://store.steampowered.com/app/262060/Darkest_Dungeon/',
        'https://store.steampowered.com/app/311690/Enter_the_Gungeon/',
        'https://store.steampowered.com/app/609490/Minit/',
        'https://store.steampowered.com/app/752590/A_Plague_Tale_Innocence/',
        'https://store.steampowered.com/app/213670/South_Park_The_Stick_of_Truth/',
        'https://store.steampowered.com/app/1824220/Chivalry_2/',
        'https://store.steampowered.com/app/322500/SUPERHOT/',
        'https://store.steampowered.com/app/13520/Far_Cry/',
        'https://store.steampowered.com/app/220240/Far_Cry_3/',
        'https://store.steampowered.com/app/298110/Far_Cry_4/',
        'https://store.steampowered.com/app/552520/Far_Cry_5/',
        'https://store.steampowered.com/app/979070/Just_Die_Already/'
    ]

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
