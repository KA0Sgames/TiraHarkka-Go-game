# Aineopintojen Harjoitustyö: Tietorakenteet ja Algoritmit
# Go peli

### Dokumentaatio:

- [Määrittelydokumentti](dokumentaatio/Maarittelydokumentti.md)
- [Toteutusdokumentti](Toteutusdokumentti.md)
- [Käyttöohje](dokumentaatio/Kayttoohje.md)
- [Testikattavuusraportti](dokumentaatio/Testikattavuusraportti.md)

Viikkoraportit:

- [Viikko 1](dokumentaatio/Viikkoraportti1.md)
- [Viikko 2](dokumentaatio/Viikkoraportti2.md)
- [Viikko 3](dokumentaatio/Viikkoraportti3.md)
- [Viikko 4](dokumentaatio/Viikkoraportti4.md)
- [Viikko 5](dokumentaatio/Viikkoraportti5.md)
- [Viikko 6](dokumentaatio/Viikkoraportti6.md)
- [Viikkopalautusten jälkeinen työ](dokumentaatio/Viikkopalautuksienjalkeen.md)

### JavaDoc:

- Javadoc voidaan generoida komennolla `gradle javadoc`
- Javadoc:ia voidaan tarkastella tiedostosta /GoPeli/build/docs/javadoc/index.html

### Testit:

- Testit voi ajaa komennolla `gradle test`
- Testien tuloksia voi tarkastella tiedostosta /GoPeli/build/reports/tests/test/index.html

- Testit ajamalla generoituu testikattavuusraportti, jota voi tutkia tiedostosta /GoPeli/build/reports/jacoco/test/html/index.html

### Ohjelmalla pelaaminen:

- Käyttöohjeessa on ohjeet miten ohjelmaa pääsee käyttämään, tällä hetkellä valitettavasti toimivaa Jar-tiedostoa ei ole.

### Puutteet ja bugit:

- Ohjelman tekoälyssä bugi, kun tekoälyn pitäisi passata niin tulee NullPointerException.
- Ohjelman käyttöliittymässä bugi. Testasin pelata peliä ja valkoisen vangit lisääntyivät, vaikka valkea ei kaapannut kiviä.
- Ohjelman toteutus on vielä liian hidas, jotta tekoäly saisi tehdyksi tarpeeksi simulaatioita, tarkemmin toteutusdokumentissa.