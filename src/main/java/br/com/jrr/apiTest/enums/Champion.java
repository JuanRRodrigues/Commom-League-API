package br.com.jrr.apiTest.enums;

public enum Champion {
    AATROX(1, "Aatrox"),
    AHRI(2, "Ahri"),
    AKALI(3, "Akali"),
    ALISTAR(4, "Alistar"),
    AMUMU(5, "Amumu"),
    ANIVIA(6, "Anivia"),
    APHELIOS(7, "Aphelios"),
    ASHE(8, "Ashe"),
    AURELION_SOL(9, "Aurelion Sol"),
    AZIR(10, "Azir"),
    BARD(11, "Bard"),
    BELVETH(12, "Bel'Veth"),
    BLITZCRANK(13, "Blitzcrank"),
    BRAND(14, "Brand"),
    BRAUM(15, "Braum"),
    CAITLYN(16, "Caitlyn"),
    CAMILLE(17, "Camille"),
    CASSIOPEIA(18, "Cassiopeia"),
    CHOGATH(19, "Cho'Gath"),
    CORKI(20, "Corki"),
    DARIUS(21, "Darius"),
    DRAVEN(22, "Draven"),
    EKKO(23, "Ekko"),
    ELISE(24, "Elise"),
    EZREAL(25, "Ezreal"),
    FIDDLSTICKS(26, "Fiddlesticks"),
    FIORA(27, "Fiora"),
    FIZZ(28, "Fizz"),
    GALIO(29, "Galio"),
    GAREN(30, "Garen"),
    GRAVES(31, "Graves"),
    HEIMERDINGER(32, "Heimerdinger"),
    ILLAOI(33, "Illaoi"),
    IRELIA(34, "Irelia"),
    IVERN(35, "Ivern"),
    JANA(36, "Janna"),
    JAX(37, "Jax"),
    JHIN(38, "Jhin"),
    JINX(39, "Jinx"),
    KAYN(40, "Kayn"),
    KENNEN(41, "Kennen"),
    KHAZIX(42, "Kha'Zix"),
    KINDRED(43, "Kindred"),
    KLED(44, "Kled"),
    KOGMAW(45, "Kog'Maw"),
    LEONA(46, "Leona"),
    LISSANDRA(47, "Lissandra"),
    LUCIAN(48, "Lucian"),
    LULU(49, "Lulu"),
    LUX(50, "Lux"),
    MALPHITE(51, "Malphite"),
    MALZAHAR(52, "Malzahar"),
    MASTER_YI(53, "Master Yi"),
    MORGANA(54, "Morgana"),
    NAUTILUS(55, "Nautilus"),
    NIDALEE(56, "Nidalee"),
    NUNU(57, "Nunu"),
    OLAF(58, "Olaf"),
    ORIANNA(59, "Orianna"),
    RAKAN(60, "Rakan"),
    RAMMUS(61, "Rammus"),
    REKSAI(62, "Rek'Sai"),
    RIVEN(63, "Riven"),
    RUMBLE(64, "Rumble"),
    RYZE(65, "Ryze"),
    SAMIRA(66, "Samira"),
    SEJUANI(67, "Sejuani"),
    SERAPHINE(68, "Seraphine"),
    SHACO(69, "Shaco"),
    SHEN(70, "Shen"),
    SHYVANA(71, "Shyvana"),
    SION(72, "Sion"),
    SIVIR(73, "Sivir"),
    SONA(74, "Sona"),
    SORAKA(75, "Soraka"),
    SWAIN(76, "Swain"),
    SYNDRA(77, "Syndra"),
    TAHM_KENCH(78, "Tahm Kench"),
    TALIYAH(79, "Taliyah"),
    TALON(80, "Talon"),
    TARIC(81, "Taric"),
    TEEMO(82, "Teemo"),
    THRESH(83, "Thresh"),
    TRISTANA(84, "Tristana"),
    TRUNDLE(85, "Trundle"),
    TRYNDAMERE(86, "Tryndamere"),
    TWITCH(87, "Twitch"),
    URGOT(88, "Urgot"),
    VAYNE(89, "Vayne"),
    VEIGAR(90, "Veigar"),
    VELKOZ(91, "Vel'Koz"),
    VEX(92, "Vex"),
    VI(93, "Vi"),
    VIKTOR(94, "Viktor"),
    VOLIBEAR(95, "Volibear"),
    WARWICK(96, "Warwick"),
    WUKONG(97, "Wukong"),
    XAYAH(98, "Xayah"),
    ZAC(99, "Zac"),
    ZED(100, "Zed"),
    ZERI(101, "Zeri"),
    ZIGGS(102, "Ziggs"),
    ZYRA(103, "Zyra");

    private final int id;
    private final String displayName;

    Champion(int id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    public int getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }
}