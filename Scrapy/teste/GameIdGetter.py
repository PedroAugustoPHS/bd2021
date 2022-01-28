def get_gameid (gameid):
    if gameid.find("Portia") != -1: #My Time At Portia
        return ('1')
    elif gameid.find("Builder") != -1: #LEGO® Builder's Journey
        return ('2')
    elif gameid.find("Farming") != -1: #Farming Simulator 22
        return ('3')
    elif gameid.find("A Lenda do Herói") != -1 or gameid.find("Songs for a Hero") != -1: #A Lenda do Herói
        return ('4')
    elif gameid.find("Among Us") != -1: #Among Us
        return ('5')
    elif gameid.find("DEATH") != -1 or gameid.find("Death") != -1: #DEATH STRANDING
        return ('6')
    elif gameid.find("while True") != -1: #while True: learn()
        return ('7')
    elif gameid.find("Mafia") != -1: #Mafia II: Definitive Edition"
        return ('8')
    elif gameid.find("Overcooked") != -1 and gameid.find("2") == -1: #Overcooked
        return ('9')
    elif gameid.find("Overcooked") != -1 and gameid.find("2") != -1: #Overcooked! 2
        return ('10')
    elif gameid.find("Never Alone") != -1: #Never Alone (Kisima Ingitchuna)
        return ('11')
    elif (gameid.find("Batman") != -1 and gameid.find("Videogame") != -1) or gameid == "LEGO Batman" != -1: #LEGO® Batman™: The Videogame
        return ('12')
    elif gameid.find("Eastward") != -1: #Eastward
        return ('13')
    elif gameid.find("Batman") != -1 and gameid.find("2") != -1: #LEGO® Batman™ 2: DC Super Heroes
        return ('14')
    elif gameid.find("PC Building") != -1: #PC Building Simulator
        return ('15')
    elif gameid.find("Red Dead") != -1: #Red Dead Redemption 2
        return ('16')
    elif gameid.find("Vampyr") != -1: #Vampyr
        return ('17')
    elif gameid.find("Batman") != -1 and gameid.find("3") != -1: #LEGO® Batman™ 3: Beyond Gotham
        return ('18')
    elif gameid.find("A Plague Tale") != -1: #A Plague Tale: Innocence
        return ('19')
    elif gameid.find("Darkest") != -1: #Darkest Dungeon®
        return ('20')
    elif gameid.find("Gungeon") != -1: #Enter the Gungeon
        return ('21')
    elif gameid.find("Minit") != -1: #Minit
        return ('22')
    elif gameid.find("South Park") != -1: #South Park™: The Stick of Truth™
        return ('23')
    elif gameid == "Far Cry" or gameid == "Far Cry 1" or gameid == "Far Cry®": #Far Cry
        return ('24')
    elif (gameid.find("Far Cry") != -1 or gameid.find("FAR") != -1) and gameid.find("3") != -1: #Far Cry®3
        return ('25')
    elif gameid.find("SUPERHOT") != -1: #SUPERHOT
        return ('26')
    elif (gameid.find("Far Cry") != -1 or gameid.find("FAR") != -1) and gameid.find("5") != -1: #Far Cry 5
        return ('27')
    elif gameid.find("Chivalry") != -1: #Chivalry 2
        return ('28')
    elif (gameid.find("Far Cry") != -1 or gameid.find("FAR") != -1) and gameid.find("4") != -1: #FAR CRY 4
        return ('29')
    elif gameid.find("Just Die") != -1: #Just Die Already
        return ('30')
    else:
        return '0'
