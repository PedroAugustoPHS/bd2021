import psycopg2
import json
import re

try:
    connection = psycopg2.connect(user="anderson",
                                  password="admin",
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

    {"SISTEMA OPERACIONAL: Windows XP SP3, Vista SP2, Windows 7ﾂｮ SP1, Windows 8 (versﾃｵes de 32 ou 64 bits)"}
    for i in data:
        title = (i["titulo"][0].replace("'",""))
        developer = (i["desenvolvedora"][0].replace("'",""))
        description = (i["descricao"][0].replace("'",""))
        publisher = (i["publicadora"][0].replace("'",""))
        date_publication = (i["ano_publicacao"][0].replace("'",""))
        cpu = (i["cpu"][0].replace("'",""))
        gpu = (i["gpu"][0].replace("'",""))
        ram = (i["memoria_ram"][0].replace("'",""))
        so = (i["so"][0].replace("'",""))
        hd = (i["armazenamento"][0].replace("'",""))

        postgres_insert_query =  "INSERT INTO bd2021.jogo (titulo, desenvolvedora, categoria, descricao, publicadora, ano_publicacao, cpu, gpu, memoria_ram, so, armazenamento, image) " \
                                 "VALUES (%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)"
        record_to_insert = (title, developer, i["categoria"], description, publisher, date_publication, cpu, gpu, ram, so, hd, i["img_src"])
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