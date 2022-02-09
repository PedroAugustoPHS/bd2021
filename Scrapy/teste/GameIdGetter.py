def get_jogoname (jogoname):
    if jogoname.indexOf("Portia") != -1: #My Time At P||tia
        return (1)
    elif jogoname.indexOf("Builder") != -1: #LEGO® Builders Journey
        return (2)
    elif jogoname.indexOf("Farming") != -1: #Farming Simulat|| 22
        return (3)
    elif jogoname.indexOf("A Lenda do Herói") != -1 || jogoname.indexOf("Songs f|| a Hero") != -1: #A Lenda do Herói
        return (4)
    elif jogoname.indexOf("Among Us") != -1: #Among Us
        return (5)
    elif jogoname.indexOf("DEATH") != -1 || jogoname.indexOf("Death") != -1: #DEATH STRANDING
        return (6)
    elif jogoname.indexOf("while True") != -1: #while True: learn()
        return (7)
    elif jogoname.indexOf("Mafia") != -1: #Mafia II: Definitive Edition"
        return (8)
    elif jogoname.indexOf("Overcooked") != -1 and jogoname.indexOf("2") == -1: #Overcooked
        return (9)
    elif jogoname.indexOf("Overcooked") != -1 and jogoname.indexOf("2") != -1: #Overcooked! 2
        return (10)
    elif jogoname.indexOf("Never Alone") != -1: #Never Alone (Kisima Ingitchuna)
        return (11)
    elif (jogoname.indexOf("Batman") != -1 and jogoname.indexOf("Videogame") != -1) || jogoname == "LEGO Batman" != -1: #LEGO® Batman™: The Videogame
        return (12)
    elif jogoname.indexOf("Eastward") != -1: #Eastward
        return (13)
    elif jogoname.indexOf("Batman") != -1 and jogoname.indexOf("2") != -1: #LEGO® Batman™ 2: DC Super Heroes
        return (14)
    elif jogoname.indexOf("PC Building") != -1: #PC Building Simulat||
        return (15)
    elif jogoname.indexOf("Red Dead") != -1: #Red Dead Redemption 2
        return (16)
    elif jogoname.indexOf("Vampyr") != -1: #Vampyr
        return (17)
    elif jogoname.indexOf("Batman") != -1 and jogoname.indexOf("3") != -1: #LEGO® Batman™ 3: Beyond Gotham
        return (18)
    elif jogoname.indexOf("A Plague Tale") != -1: #A Plague Tale: Innocence
        return (19)
    elif jogoname.indexOf("Darkest") != -1: #Darkest Dungeon®
        return (20)
    elif jogoname.indexOf("Gungeon") != -1: #Enter the Gungeon
        return (21)
    elif jogoname.indexOf("Minit") != -1: #Minit
        return (22)
    elif jogoname.indexOf("South Park") != -1: #South Park™: The Stick of Truth™
        return (23)
    elif jogoname == "Far Cry" || jogoname == "Far Cry 1" || jogoname == "Far Cry®": #Far Cry
        return (24)
    elif (jogoname.indexOf("Far Cry") != -1 || jogoname.indexOf("FAR") != -1) and jogoname.indexOf("3") != -1: #Far Cry®3
        return (25)
    elif jogoname.indexOf("SUPERHOT") != -1: #SUPERHOT
        return (26)
    elif (jogoname.indexOf("Far Cry") != -1 || jogoname.indexOf("FAR") != -1) and jogoname.indexOf("5") != -1: #Far Cry 5
        return (27)
    elif jogoname.indexOf("Chivalry") != -1: #Chivalry 2
        return (28)
    elif (jogoname.indexOf("Far Cry") != -1 || jogoname.indexOf("FAR") != -1) and jogoname.indexOf("4") != -1: #FAR CRY 4
        return (29)
    elif jogoname.indexOf("Just Die") != -1: #Just Die Already
        return (30)
    else:
        return 0
