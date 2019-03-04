--Script automatically executed on startup by Hibernate
DROP TABLE IF EXISTS parties;
DROP TABLE IF EXISTS funds;
DROP TABLE IF EXISTS trades;

CREATE TABLE parties (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(255),
    shortname varchar(16),
    PRIMARY KEY (id)
);

CREATE TABLE funds (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(255),
    shortname varchar(16),
    units float,
    party_id int,
    PRIMARY KEY (id),
    FOREIGN KEY (party_id) REFERENCES parties(id)
);

CREATE TABLE trades (
    id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
    src_fund_id int,
    dest_fund_id int,
    units float,
    internal BOOLEAN,
    --FOREIGN KEY (src_fund_id) REFERENCES funds(id),
    FOREIGN KEY (dest_fund_id) REFERENCES funds(id)
);


INSERT INTO parties(id, shortname, name) VALUES (1001, 'A', 'Agilent Technologies');
INSERT INTO parties(shortname, name) VALUES('AAC', 'Aac Holdings Inc');
INSERT INTO parties(shortname, name) VALUES('AAN', 'Aarons Inc');
INSERT INTO parties(shortname, name) VALUES('AAP', 'Advance Auto Parts Inc');
INSERT INTO parties(shortname, name) VALUES('AAT', 'American Assets Trust');
INSERT INTO parties(shortname, name) VALUES('AB', 'Alliancebernstein Holding LP');
INSERT INTO parties(shortname, name) VALUES('ABB', 'Abb Ltd');
INSERT INTO parties(shortname, name) VALUES('ABBV', 'Abbvie Inc');
INSERT INTO parties(shortname, name) VALUES('ABC', 'Amerisourcebergen Corp');
INSERT INTO parties(shortname, name) VALUES('AA', 'Alcoa Corp');
INSERT INTO parties(shortname, name) VALUES('ABEV', 'Ambev S.A.');
INSERT INTO parties(shortname, name) VALUES('ABG', 'Asbury Automotive Group Inc');
INSERT INTO parties(shortname, name) VALUES('ABM', 'ABM Industries Incorporated');
INSERT INTO parties(shortname, name) VALUES('B', 'Barnes Group');
INSERT INTO parties(shortname, name) VALUES('BA', 'Boeing Company');
INSERT INTO parties(shortname, name) VALUES('BABA', 'Alibaba Group Holding');
INSERT INTO parties(shortname, name) VALUES('BAC', 'Bank of America Corp');
INSERT INTO parties(shortname, name) VALUES('BAC-A', 'Bank of America Corp Pfd A');
INSERT INTO parties(shortname, name) VALUES('BAC-B', 'Bank of America Corp. Dep Shs Repstg');
INSERT INTO parties(shortname, name) VALUES('BAC-C', 'Bank of America Corp Pfd C');
INSERT INTO parties(shortname, name) VALUES('BAC-E', 'Bank of America Corp Dep R');
INSERT INTO parties(shortname, name) VALUES('BAC-K', 'Bank of America Corp Dep Shs Repstg');
INSERT INTO parties(shortname, name) VALUES('BAC-L', 'Bank of America Corp Pfd L');
INSERT INTO parties(shortname, name) VALUES('BAC-W', 'Bank of America Corp Pfd W');
INSERT INTO parties(shortname, name) VALUES('BAC-Y', 'Bank of America Corp Pfd Y');
INSERT INTO parties(shortname, name) VALUES('BAC.A', 'Bank of America Corp Cl A');
INSERT INTO parties(shortname, name) VALUES('BAF', 'Blackrock Income Inv Quality Trust');
INSERT INTO parties(shortname, name) VALUES('BAH', 'Booz Allen Hamilton Holding Corp');
INSERT INTO parties(shortname, name) VALUES('BAK', 'Braskem S.A.');
INSERT INTO parties(shortname, name) VALUES('BAM', 'Brookfield Asset Management Inc');
INSERT INTO parties(shortname, name) VALUES('BAN-D', 'Bank of California Inc Pref Share Series');
INSERT INTO parties(shortname, name) VALUES('BAN-E', 'Banc of California Inc');
INSERT INTO parties(shortname, name) VALUES('BANC', 'First Pactrust Bancorp');
INSERT INTO parties(shortname, name) VALUES('BAP', 'Credicorp Ltd');
INSERT INTO parties(shortname, name) VALUES('BAS', 'Basic Energy Services');
INSERT INTO parties(shortname, name) VALUES('BAX', 'Baxter International Inc');
INSERT INTO parties(shortname, name) VALUES('MS-K', 'Morgan Stanley Dep Shs Repstg');
INSERT INTO parties(shortname, name) VALUES('MSA', 'Msa Safety Inc');
INSERT INTO parties(shortname, name) VALUES('MSB', 'Mesabi Trust');
INSERT INTO parties(shortname, name) VALUES('MSC', 'Studio City Intl Holdings Ltd ADR');
INSERT INTO parties(shortname, name) VALUES('GOOGL', 'Google Inc.');
INSERT INTO parties(shortname, name) VALUES('MSD', 'Morgan Stanley Emerging Markets Debt');
INSERT INTO parties(shortname, name) VALUES('MSF', 'Morgan Stanley Emerging Markets Fund Inc');
INSERT INTO parties(shortname, name) VALUES('MSG', 'The Madison Square Garden Comp');
INSERT INTO parties(shortname, name) VALUES('MSGN', 'Msg Networks Inc');
INSERT INTO parties(shortname, name) VALUES('MSI', 'Motorola Solutions');
INSERT INTO parties(shortname, name) VALUES('MSL', 'Midsouth Bancorp');
INSERT INTO parties(shortname, name) VALUES('MSM', 'Msc Industrial Direct Company');
INSERT INTO parties(shortname, name) VALUES('MT', 'Arcelormittal');
INSERT INTO parties(shortname, name) VALUES('MTB', 'M&T Bank Corp');
INSERT INTO parties(shortname, name) VALUES('MTB-C', 'M&T Bank Corporation Fixed Rate');
INSERT INTO parties(shortname, name) VALUES('MTB.P', 'M&T Bank Corporation Fixed Rate');

INSERT INTO funds(id, shortname, name, units, party_id) VALUES (2001, 'MUSA', 'Murphy USA Inc', 2001.24, 1022);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('RAD', 'Rite Aid Corp', 0.7100, 1011);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('RAMP', 'Liveramp Holdings Inc.', 38.35, 1011);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('RBA', 'Ritchie Bros. Auctioneers Inc', 31.33, 1011);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('RBC', 'Regal-Beloit Corp', 69.18, 1021);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('X', 'United States Steel Corp', 17.46, 1021);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('XAN', 'Exantas Capital Corp', 9.760, 1021);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('XAN-C', 'Exantas Capital Corp. Pfd', 23.85, 1021);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('XEC', 'Cimarex Energy Co', 59.52, 1021);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('XFLT', 'Xai Octagon Floating Alt Income Term', 7.300, 1021);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('XHR', 'Xenia Hotels & Resorts Inc', 16.66, 1002);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('MZA', 'Muniyield Arizona Fund', 103, 1041);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('MX', 'Magnachip Semiconductor Corp', 84, 1041);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('MXF', 'Mexico Fund', 334, 1006);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('R', 'Ryder System', 46.23, 1011);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('RA', 'Brookfield Real Assets Income Fund Inc', 18.33, 1011);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('RACE', 'Ferrari N.V.', 97.87, 1011);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('XIN', 'Xinyuan Real Estate Co Ltd', 3.800, 1003);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('XOM', 'Exxon Mobil Corp', 66.30, 1004);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('XOXO', 'Xoxo Group Inc', 35.00, 1005);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('XPO', 'Xpo Logistics Inc', 51.79, 1006);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('XRF', 'China Rapid Finance Limited ADR', 1.200, 1007);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('XRX', 'Xerox Corp', 19.48, 1008);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('XYF', 'X Financial', 5.200, 1009);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('XYL', 'Xylem Inc', 61.74, 1010);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('RBS', 'Royal Bank Scotland Group Plc', 5.250, 1021);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('RBS-S', 'Royal Bank Scotland', 25.17, 1021);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('RC', 'Ready Capital Corp.', 13.97, 1021);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('RCA', 'Ready Capital Corp. 7.00%', 24.46, 1001);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('UA', 'Under Armour Inc Class C Comm', 15.79, 1010);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('UAA', 'Under Armour', 17.36, 1010);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('UAN', 'Cvr Partners LP', 3.390, 1010);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('UBA', 'Urstadt Biddle Properties Inc', 19.94, 1010);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('UBP', 'Urstadt Biddle Properties Inc', 15.72, 1010);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('UBP-G', 'Urstadt Biddle Properties Inc', 24.77, 1011);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('UBP-H', 'Urstadt Biddle Properties Inc Pfd.', 23.67, 1012);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('UBS', 'UBS Group Ag', 12.08, 1013);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('UDR', 'United Dominion Realty Trust', 40.80, 1014);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('UE', 'Urban Edge Properties', 16.84, 1015);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('UFI', 'Unifi Inc', 22.33, 1016);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('UFS', 'Domtar Corp', 35.25, 1017);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('UGI', 'Ugi Corp', 56.21, 1018);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('UGP', 'Ultrapar Participacoes S.A.', 12.78, 1019);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('UHS', 'Universal Health Services', 115.0, 1020);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('UHT', 'Universal Health Realty Income Trust', 61.07, 1021);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('UIS', 'Unisys Corp', 11.08, 1022);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('UL', 'Unilever Plc', 52.62, 1023);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('UMC', 'United Microelectronics Corp', 1.790, 1024);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('UMH', 'Umh Properties', 11.92, 1025);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('UMH-B', 'Umh Properties Inc', 25.63, 1026);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('UMH-C', 'Umh Properties Inc', 23.18, 1027);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('UMH-D', 'Umh Properties Inc Cum Red Pfd Ser D', 22.00, 1028);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('UN', 'Unilever Nv', 53.96, 1029);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('UNF', 'Unifirst Corp', 136.7, 1029);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('UNH', 'Unitedhealth Group Inc', 237.9, 1031);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('UNM', 'Unumprovident Corp', 28.07, 1001);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('UNMA', 'Unum Group 6.250% Junior Subordinated', 22.95, 1002);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('UNP', 'Union Pacific Corp', 132.5, 1003);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('UNT', 'Unit Corp', 14.17, 1004);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('UNVR', 'Univar Inc', 16.92, 1005);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('UPS', 'United Parcel Service', 93.35, 1006);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('URI', 'United Rentals', 99.57, 1007);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('USA', 'Liberty All-Star Equity Fund', 5.000, 1008);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('USAC', 'USA Compression Partners LP', 13.01, 1009);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('USB', 'U.S. Bancorp', 44.64, 1010);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('USB-A', 'U.S. Bancorp Depositary Shares', 770.0, 1011);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('USB-H', 'U.S. Bancorp Dep Sh', 18.67, 1012);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('USB-M', 'U.S. Bancorp', 26.70, 1013);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('USB-O', 'U.S. Bancorp', 22.94, 1014);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('USB-P', 'US Bancorp [De] Depositary Shs Repstg', 24.59, 1015);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('USDP', 'USD Partners LP', 10.43, 1016);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('USFD', 'US Foods Holding', 30.33, 1017);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('USG', 'USG Corp', 42.90, 1018);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('USM', 'United States Cellular Corp', 49.69, 1019);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('USNA', 'Usana Health Sciences Inc', 113.4, 1020);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('USPH', 'U.S. Physical Therapy', 100.58, 1021);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('USX', 'U.S. Xpress Enterprises Inc. Class A', 5.100, 1022);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('UTF', 'Cohen & Steers Infrastructure Fund', 19.04, 1023);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('UTI', 'Universal Technical Institute Inc', 3.485, 1024);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('UTL', 'Unitil Corp', 51.95, 1025);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('UTX', 'United Technologies Corp', 105.7, 1026);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('UVE', 'Universal Insurance Holdings Inc', 37.13, 1027);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('UVV', 'Universal Corp', 56.10, 1028);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('UZA', 'United States Cellular Corp', 22.40, 1029);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('UZB', 'United States Cellular Corpora', 24.14, 1030);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('UZC', 'United States Cellular Corpora', 24.52, 1031);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('L', 'Loews Corp', 43.87, 1032);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LAC', 'Lithium Americas Corp', 2.950, 1033);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LAD', 'Lithia Motors', 71.10, 1034);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LADR', 'Ladder Capital Corp', 15.25, 1035);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LAIX', 'Laix Inc. ADR', 7.840, 1036);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LAZ', 'Lazard Ltd', 35.23, 1037);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LB', 'L Brands Inc', 25.19, 1038);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LBRT', 'Liberty Oilfield Services Inc', 13.30, 1039);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LC', 'Lendingclub Corp', 2.620, 1040);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LCI', 'Lannett Co Inc', 4.810, 1041);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LCII', 'Lci Industries', 63.17, 1042);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LDL', 'Lydall Inc', 19.81, 1043);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LDOS', 'Leidos Holdings Inc', 51.41, 1044);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LDP', 'Cohen & Steers Ltd Duration Prfd Income', 20.61, 1043);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LEA', 'Lear Corp', 119.4, 1042);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LEAF', 'Leaf Group Ltd', 7.390, 1041);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LEE', 'Lee Enterprises Inc', 2.035, 1040);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LEG', 'Leggett & Platt Inc', 35.02, 1039);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LEJU', 'Leju Holdings Ltd', 1.280, 1038);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LEN', 'Lennar Corp', 38.92, 1037);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LEN.B', 'Lennar Corp Cl B', 31.27, 1036);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LEO', 'Dreyfus Strategic Municipals', 7.150, 1035);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LFC', 'China Life Insurance Company Ltd', 10.40, 1034);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LGC', 'Legacy Acquisition Corp', 9.800, 1033);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LGC.U', 'Legacy Acquisition Corp Units', 10.10, 1032);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LGC.W', 'Legacy Acquisition Corp. WT', 0.3100, 1031);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LGF.A', 'Lions Gate Entertainment Corp Cl A', 14.76, 1030);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LGF.B', 'Lions Gate Entertainment Corp Cl B', 14.04, 1029);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LGI', 'Lazard Global Total Return and', 13.01, 1028);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LH', 'Laboratory Corporation of America', 123.4, 1027);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LHC', 'Leo Holdings Corp. Class A', 9.700, 1026);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LHC.U', 'Leo Holdings Corp. Units Each Consisting', 10.21, 1025);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LHC.W', 'Leo Holdings Corp Warrants', 1.170, 1024);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LHO', 'Lasalle Hotel Properties', 32.49, 1023);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LHO-I', 'Lasalle Hotel Properties', 23.49, 1022);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LHO-J', 'Lasalle Hotel Properties', 22.35, 1021);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LII', 'Lennox International', 209.6, 1020);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LIN', 'Linde Plc', 153.4, 1019);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LITB', 'Lightinthebox Holding Co. Ltd', 1.465, 1018);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LKM', 'Link Motion Inc', 0.2400, 1017);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LKSD', 'Lsc Communications Inc', 7.250, 1016);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LL', 'Lumber Liquidators Holdings Inc', 10.110, 1015);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LLL', 'L-3 Communications Holdings', 166.7, 1014);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LLY', 'Eli Lilly and Company', 109.4, 1013);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LM', 'Legg Mason Inc', 24.20, 1012);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LMHA', 'Legg Mason Inc', 23.95, 1011);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LMHB', 'Legg Mason Inc', 20.02, 1010);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LMT', 'Lockheed Martin Corp', 254.6, 1009);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LN', 'Line Corp', 32.88, 1008);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LNC', 'Lincoln National Corp', 49.77, 1007);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LNC.W', 'Lincoln National Corp', 55.50, 1006);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LND', 'Brasilagro Brazi ADR', 4.090, 1005);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LNN', 'Lindsay Corp', 91.94, 1004);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LNT', 'Alliant Energy Corp', 43.59, 1003);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LOMA', 'Loma Negra Comp Indu Argentina Sociedad', 10.030, 1002);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LOR', 'Lazard World Dividend &', 8.170, 1003);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LOW', 'Lowes Companies', 88.38, 1004);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LPG', 'Dorian Lpg Ltd', 6.010, 1005);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LPI', 'Laredo Petroleum Holdings Inc', 3.300, 1006);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LPL', 'Lg Display Co. Ltd', 8.150, 1007);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LPT', 'Liberty Property Trust', 41.98, 1008);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LPX', 'Louisiana-Pacific Corp', 21.32, 1009);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LRN', 'K12 Inc', 22.84, 1010);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LSI', 'Life Storage', 95.80, 1011);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LTC', 'Ltc Properties', 42.90, 1012);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LTHM', 'Livent Corporation', 13.09, 1013);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LTM', 'Latam Airlines Group S.A.', 9.860, 1014);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LTN', 'Union Acquisition Corp', 10.05, 1015);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LTN.P', 'Union Acquisition Corp Rights', 0.3600, 1016);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LTN.U', 'Union Acquisition Corp', 10.70, 1017);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LTN.W', 'Union Acquisition Corp. Warrant', 0.2600, 1018);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LUB', 'Lubys Inc', 1.440, 1019);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LUV', 'Southwest Airlines Company', 45.69, 1020);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LVS', 'Las Vegas Sands', 49.35, 1021);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LW', 'Lamb Weston Holdings Inc', 74.43, 1022);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LXFR', 'Luxfer Holdings Plc', 16.78, 1023);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LXFT', 'Luxoft Holding Inc', 30.65, 1024);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LXP', 'Lexington Realty Trust', 8.140, 1025);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LXP-C', 'Lexington Realty Tru', 48.75, 1026);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LXU', 'Lsb Industries Inc', 5.560, 1027);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LYB', 'Lyondellbasell Industries Nv', 80.61, 1028);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LYG', 'Lloyds Banking Group Plc', 2.510, 1029);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LYV', 'Live Nation Entertainment', 48.65, 1030);
INSERT INTO funds(shortname, name, units, party_id) VALUES ('LZB', 'La-Z-Boy Inc', 26.06, 1031);

INSERT INTO trades(id, src_fund_id, dest_fund_id, units, internal) VALUES (3001, 1025, 2001, 123.44, TRUE);
INSERT INTO trades(src_fund_id, dest_fund_id, units, internal) VALUES (2005, 2003, 6633, FALSE);
INSERT INTO trades(src_fund_id, dest_fund_id, units, internal) VALUES (2009, 2002, 81, FALSE);
INSERT INTO trades(src_fund_id, dest_fund_id, units, internal) VALUES (2009, 2002, 8100, TRUE);
INSERT INTO trades(src_fund_id, dest_fund_id, units, internal) VALUES (2011, 2002, 8103, FALSE);
INSERT INTO trades(src_fund_id, dest_fund_id, units, internal) VALUES (2011, 2019, 8102, TRUE);
