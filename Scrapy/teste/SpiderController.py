import psycopg2
import json

try:
    connection = psycopg2.connect(user="postgres",
                                  password="postgres",
                                  host="localhost",
                                  port="5432",
                                  database="kaster")
    cursor = connection.cursor()
    #Lendo json
    # Opening JSON file
    file_name = "nuuvem-24-01.json"
    fopen = "spiders/" + file_name
    loja_name = file_name.split('-')[0]
    scrap_date = file_name.split('-')[1] + '/' + (file_name.split('-')[2]).split('.')[0] + '/2022'
    loja_id = 1 if loja_name == 'epic' else (2 if loja_name == 'steam' else 3)

    f = open(fopen)

    # returns JSON object as
    # a dictionary
    data = json.load(f)

    # Iterating through the json
    # list
    for index, i in data:

        postgres_insert_query =  "INSERT INTO preco_data (data_registro, preco, porcentagem_promo, jogo_id, loja_id) VALUES (%s,%s,%s,%s,%s)"
        record_to_insert = (scrap_date,float(i['preco'].replace(',','.')),int(i['desconto']), index, loja_id)
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