import psycopg2
import json
import re

try:
    connection = psycopg2.connect(user="postgres",
                                  password="postgres",
                                  host="localhost",
                                  port="5432",
                                  database="kaster")
    cursor = connection.cursor()
    #Lendo json
    # Opening JSON file
    file_name = "geral-17-01.json"
    fopen = "spiders/" + file_name

    f = open(fopen, encoding='utf-8')
    # returns JSON object as
    # a dictionary
    data = json.load(f)
    # Iterating through the json
    # list

    for i in data:
        title = (i["titulo"][0].replace("'",""))
        developer = (i["desenvolvedora"][0].replace("'",""))
        try:
            category = (i["categoria"].replace("'",""))
        except:
            category = (i["categoria"][0].replace("'",""))

        description = (i["descricao"][0].replace("'",""))
        publisher = (i["publicadora"][0].replace("'",""))
        date_publication = (i["ano_publicacao"][0].replace("'",""))
        try:
            cpu = (i["cpu"].replace("'",""))
        except:
            cpu = (i["cpu"][0].replace("'",""))

        try:
            gpu = (i["gpu"].replace("'",""))
        except:
            gpu = (i["gpu"][0].replace("'",""))

        try:
            ram = (i["memoria_ram"].replace("'",""))
        except:
            ram = (i["memoria_ram"][0].replace("'",""))

        try:
            so = (i["so"].replace("'",""))
        except:
            so = (i["so"][0].replace("'",""))

        try:
            hd = (i["armazenamento"].replace("'",""))
        except:
            hd = (i["armazenamento"][0].replace("'",""))

        postgres_insert_query =  "INSERT INTO bd2021.jogo (titulo, desenvolvedora, categoria, descricao, publicadora, ano_publicacao, cpu, gpu, memoria_ram, so, armazenamento, image) " \
                                 "VALUES (%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)"
        record_to_insert = (title, developer, category, description, publisher, date_publication, cpu, gpu, ram, so, hd, i["img_src"])
        cursor.execute(postgres_insert_query, record_to_insert)

    #Closing file
    f.close()


    connection.commit()
    count = cursor.rowcount
    print(count, "Record inserted successfully into jogo")

except (Exception, psycopg2.Error) as error:
    print("Failed to insert record into jogo", error)

finally:
    # closing database connection.
    if connection:
        cursor.close()
        connection.close()
        print("PostgreSQL connection is closed")