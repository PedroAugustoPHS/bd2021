import psycopg2
import json
from GameIdGetter import get_gameid

try:
    connection = psycopg2.connect(user="postgres",
                                  password="postgres",
                                  host="localhost",
                                  port="5432",
                                  database="kaster")
    cursor = connection.cursor()
    #Lendo json
    # Opening JSON file
    file_name = "nuuvem-28-01.json"
    fopen = "spiders/" + file_name
    loja_name = file_name.split('-')[0]
    scrap_date ='2022' + '/' + (file_name.split('-')[2]).split('.')[0] + '/' + file_name.split('-')[1] #yy/mm/dd
    loja_id = 1 if loja_name == 'epic' else (2 if loja_name == 'steam' else 3)

    f = open(fopen, encoding='utf-8')
    # returns JSON object as
    # a dictionary
    data = json.load(f)
    # Iterating through the json
    # list
    for i in data:
        if isinstance(i['titulo'], (list,tuple)):
            game_id = get_gameid(i['titulo'][0])
        else:
            game_id = get_gameid(i['titulo'])
        try:
            discount = (i['desconto']).replace('-','').replace('%','')
        except:
            discount = (i['desconto'][0]).replace('-','').replace('%','')
        try:
            price = i['preco'].replace(',','.').replace('R$','')
        except:
            price = i['preco'][0].replace(',','.').replace('R$','')
        if game_id == '0':
            continue
        postgres_insert_query =  "INSERT INTO bd2021.preco_data (data_registro, preco, porcentagem_promo, jogo_id, loja_id) VALUES (%s,%s,%s,%s,%s)"
        record_to_insert = (scrap_date,float(price),int(discount), game_id, loja_id)
        cursor.execute(postgres_insert_query, record_to_insert)

    #Closing file
    f.close()


    connection.commit()
    count = cursor.rowcount
    print(count, "Record inserted successfully into preco_data")

except (Exception, psycopg2.Error) as error:
    print("Failed to insert record into preco_data", error)

finally:
    # closing database connection.
    if connection:
        cursor.close()
        connection.close()
        print("PostgreSQL connection is closed")