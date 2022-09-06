# Raportti viikkopalautuksien jälkeen tehdystä työstä

### Mitä olen tehnyt?

- 28.8. Tekoälylle kirjoitettu metodi simuloiSattumanvarainenPeli. Noin 3 tuntia.
- 28.8. Tekoälyn lisääminen käyttöliittymään. Bugin etsintää tekoälystä. Yksi tunti.
- 29.8. Yritin korjata bugia tekoälystä. Noin 3 tuntia.
- 30.8. Korjasin tekoälyn bugit (loputon looppi pelin simuloinnissa, sekä joidenkin listojen väärästä käsittelystä johtuvat null pointterit. Muokkasin käyttöliittymän toimimaan korjatun tekoälyn kanssa ja lisäsin viimeisen siirron merkin. Yhteensä 3,5 tuntia.
- 31.8. Demotilaisuuteen osallistuminen. Noin 1,5 tuntia.
- 3.9. Kirjoitin testejä Tekoaly-luokalle. Noin 3 tuntia.
- 5.9. Muokkasin Koordinaatti-luokan Javadoc kommentit lopulliseen muotoon ja kirjoitin Pelilauta- ja Pelitilanne-luokkien Javadoc kommentit. Yhteensä 2 tuntia.
- 6.9. Kirjoitin Javadoc kommentit Ryhma-, Siirto-, Vari ja MCPHSolmu-luokille. Lisäksi erotin viikkoraportin 6 ja sen jälkeisen työn omiksi dokumenteikseen. Yhteensä 4 tuntia.

###### Käyttämäni aika yhteensä 21 tuntia.

### Miten ohjelma on edistynyt?

- 28.8. Kirjoitettu tekoälylle simuloiSattumanvarainenPeli-metodi. Jos korjattavaa ei ilmene, niin tekoälyn pitäisi olla valmis.
- 30.8. Tekoäly korjattu toimimaan niin, että se tuottaa tuloksia (tulosten eli siirtojen järkevyys vielä hakusessa tehokkuudesta johtuen).
- 3.9. Testejä kirjoitettu Tekoaly-luokalle.
- 5.9. Koordinaatti-, Pelilauta- ja Pelitilanne-luokkien Javadoc kommentit kirjoitettu.

### Mitä olen oppinut?

### Mikä jäi epäselväksi tai on tuottanut vaikeuksia?

- 28.8. Jostain syystä käyttöliittymä jää nyt jumiin kun koitetaan klikata ensimmäistä siirtoa. Edes oma siirto ei tule näkyville, vaikka eventissä pitäisi olla ruudun päivitys ennen tekoälyn siirron hakua.
- 29.8. En jostain syystä löydä, miksi pelin simulointi jää looppiin. Jostain syystä se jossain vaiheessa simulaatiota passaa, mutta ei kahdesti peräkkäin ja vaikka tekoäly on passannut edellisellä siirrollaan, se ei passaa uusiksi seuraavalla.
- 30.8. En osaa sanoa mistä loputon looppi johtui, joten jouduin toteuttamaan naiivin tekoälyn pelin simuloinnissa hieman tehottomalla tavalla ja täten Monte Carlo algoritmi valitsee lähes sattumanvaraisia siirtoja kun simuloituja pelejä ei tule tarpeeksi.
- 3.9. Tekoaly-luokassa bugi, kun tekoälyn pitäisi passata, tulee NullPointerException.

### Mitä teen seuraavaksi?

- 28.8. Etsi bugi tekoälystä, yksi null pointer exceptionin aiheuttava löydetty, mutta nyt käyttöliittymä jää jumiin, lisäämättä siirtoja.
- 30.8. Demotilaisuus johon osallistun on huomenna, joten joudun esittelemään työn tällaisenaan. Se jollain mittarilla toimii, mutta tehokkuutta pitäisi parantaa. Tehokkuutta saattaisi hieman parantaa Zobrist hashingin käyttö ko-tilanteiden tarkistuksessa sen sijaan, että käydään koko vanha pelipuu läpi.
- 3.9. Kirjoitan dokumentaation.
- 5.9. Loppujen luokkien Javadoc kommenttien kirjoitus, sekä muiden dokumentointien kirjoitus.
- 6.9. Tekoaly-luokan Javadoc kommenttien kirjoitus, sekä varsinaisten dokumenttien kirjoittaminen.