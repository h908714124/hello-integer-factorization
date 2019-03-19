# hello-integer-factorization

Factoring `10^n - 1`

n   | factors
--- | ---
1   | 3, 3
2   | 3, 3, 11
3   | 3, 3, 3, 37
4   | 3, 3, 11, 101
5   | 3, 3, 41, 271
6   | 3, 3, 3, 7, 11, 13, 37
7   | 3, 3, 239, 4649
8   | 3, 3, 11, 73, 101, 137
9   | 3, 3, 3, 3, 37, 333667
10  | 3, 3, 11, 41, 271, 9091
11  | 3, 3, 21649, 513239
12  | 3, 3, 3, 7, 11, 13, 37, 101, 9901
13  | 3, 3, 53, 79, 265371653
14  | 3, 3, 11, 239, 4649, 909091
15  | 3, 3, 3, 31, 37, 41, 271, 2906161
16  | 3, 3, 11, 17, 73, 101, 137, 5882353
17  | 3, 3, 2071723, 5363222357
18  | 3, 3, 3, 3, 7, 11, 13, 19, 37, 52579, 333667
19  | 3, 3, 1111111111111111111
20  | 3, 3, 11, 41, 101, 271, 3541, 9091, 27961
21  | 3, 3, 3, 37, 43, 239, 1933, 4649, 10838689
22  | 3, 3, 11, 11, 23, 4093, 8779, 21649, 513239
23  | 3, 3, 11111111111111111111111
24  | 3, 3, 3, 7, 11, 13, 37, 73, 101, 137, 9901, 99990001
25  | 3, 3, 41, 271, 21401, 25601, 182521213001
26  | 3, 3, 11, 53, 79, 859, 265371653, 1058313049
27  | 3, 3, 3, 3, 3, 37, 757, 333667, 440334654777631
28  | 3, 3, 11, 29, 101, 239, 281, 4649, 909091, 121499449
29  | 3, 3, 3191, 16763, 43037, 62003, 77843839397
30  | 3, 3, 3, 7, 11, 13, 31, 37, 41, 211, 241, 271, 2161, 9091, 2906161
31  | 3, 3, 2791, 6943319, 57336415063790604359
32  | 3, 3, 11, 17, 73, 101, 137, 353, 449, 641, 1409, 69857, 5882353
33  | 3, 3, 3, 37, 67, 21649, 513239, 1344628210313298373
34  | 3, 3, 11, 103, 4013, 2071723,  5363222357, 21993833369
35  | 3, 3, 41, 71, 239, 271, 4649, 123551, 102598800232111471
36  | 3, 3, 3, 3, 7, 11, 13, 19, 37, 101, 9901, 52579, 333667, 999999000001
37  | 3, 3, 2028119, 247629013, 2212394296770203368013
38  | 3, 3, 11, 1010101010101010101010101010101010101*
39  | 3, 3, 3, 37, 53, 79, 265371653, 900900900900990990990991
40  | 3, 3, 11, 41, 73, 101, 137, 271, 3541, 9091, 27961, 1676321, 5964848081
41  | 3, 3, 83, 1231, 538987, 201763709900322803748657942361
42  | 3, 3, 3, 7, 7, 11, 13, 37, 43, 127, 239, 1933, 2689, 4649, 459691, 909091, 10838689
43  | 3, 3, 173, 1527791, 4203852214522105994074156592890477*
44  | 3, 3, 11, 11, 23, 89, 101, 4093, 8779, 21649, 513239, 1112470797641561909*
45  | 3, 3, 3, 3, 31, 37, 41, 271, 238681, 333667, 2906161, 4185502830133110721
46  | 3, 3, 11, 47, 139, 2531, 6108857605465744444444383355868389787*
47  | 3, 3, 35121409, 316362908763458525001406154038726382279
48  | 3, 3, 3, 7, 11, 13, 17, 37, 73, 101, 137, 9901, 5882353, 99990001, 9999999900000001
49  | 3, 3, 239, 4649, 1000000100000010000001000000100000010000001*
50  | 3, 3, 11, 41, 251, 271, 5051, 9091, 21401, 25601, 14396532879144434243285201*
51  | 3, 3, 3, 37, 613, 210631, 2071723, 52986961, 70623794576515816214160089*
52  | 3, 3, 11, 53, 79, 101, 521, 859, 265371653, 2011199044107865681001881398269*
53  | 3, 3, 107, 1659431, 62576967597282605945326429569432422392093283*
54  | 3, 3, 3, 3, 3, 7, 11, 13, 19, 37, 757, 52579, 333667, 70541929, 6242169169165991273965639*
55  | 3, 3, 41, 271, 1321, 21649, 62921, 513239, 83251631, 1300635692678058358830121
56  | 3, 3, 11, 29, 73, 101, 137, 239, 281, 4649, 7841, 909091, 121499449, 127522001020150503761
57  | 3, 3, 3, 37, 21319, 10749631, 4367914469227921530648229664188318958002609*
58  | 3, 3, 11, 59, 3191, 16763, 43037, 62003, 11994428258397534668721109399195445053*
59  | 3, 3, 11111111111111111111111111111111111111111111111111111111111*
60  | 3, 3, 3, 7, 11, 13, 31, 37, 41, 61, 101, 211, 241, 271, 2161, 3541, 9091, 9901, 27961, 2906161, 4188901, 39526741
61  | 3, 3, 733, 4637, 329401, 974293, 1018595901689638200825796208770072767996587*
62  | 3, 3, 11, 2791, 6943319, 52124013694355094871818181818187030583187617327669*
63  | 3, 3, 3, 3, 37, 43, 239, 1933, 4649, 10837, 23311, 45613, 333667, 10838689, 45121231, 1921436048294281
64  | 3, 3, 11, 17, 73, 101, 137, 353, 449, 641, 1409, 19841, 69857, 976193, 5882353, 6187457, 834427406578561
65  | 3, 3, 41, 53, 79, 271, 265371653, 900009000090090900909009099090990909909999099991*
66  | 3, 3, 3, 7, 11, 11, 13, 23, 37, 67, 4093, 8779, 21649, 513239, 147761341791192537461208953746267179103*
67  | 3, 3, 493121, 2253222051202668535939680344400484082225480381308261280925191*
68  | 3, 3, 11, 101, 103, 4013, 2071723, 28559389, 408937038689134166905376867775982278614490397*
69  | 3, 3, 3, 37, 277, 3613722025274371844768956682314083036104696754516249101086646213*
70  | 3, 3, 11, 41, 71, 239, 271, 4649, 9091, 123551, 909091, 4147571, 27210514393617801994618420840059911*
71  | 3, 3, 11111111111111111111111111111111111111111111111111111111111111111111111*
72  | 3, 3, 3, 3, 7, 11, 13, 19, 37, 73, 101, 137, 3169, 9901, 52579, 98641, 333667, 99990001, 3199041397329371673827370769*
73  | 3, 3, 1111111111111111111111111111111111111111111111111111111111111111111111111*
74  | 3, 3, 11, 7253, 2028119, 247629013, 277301467326398276326159708208515598588827122278808811*
75  | 3, 3, 3, 31, 37, 41, 151, 271, 4201, 21401, 25601, 2906161, 2877261764998717941949926887828840788062226151*
76  | 3, 3, 11, 101, 1000100010001000100010001000100010001000100010001000100010001000100010001*
77  | 3, 3, 239, 4649, 5237, 21649, 42043, 513239, 29920507, 136614668576002329371496447555915740910181043
78  | 3, 3, 3, 7, 11, 13, 13, 37, 53, 79, 157, 859, 6397, 216451, 265371653, 370741180002915385070252543790759277506260587*
79  | 3, 3, 317, 6163, 10271, 307627, 179998498007192867978127821006182312868256693827421272815886173*
80  | 3, 3, 11, 17, 41, 73, 101, 137, 271, 3541, 9091, 27961, 1676321, 5070721, 5882353, 117633133855156294075039863860622161*
81  | 3, 3, 3, 3, 3, 3, 37, 163, 757, 9397, 333667, 2462401, 38915803460749347518893563175144290863393089385609948107*
82  | 3, 3, 11, 83, 1231, 538987, 1834215544548389124987799476009090909090927433064536392982158968903851*
83  | 3, 3, 11111111111111111111111111111111111111111111111111111111111111111111111111111111111*
84  | 3, 3, 3, 7, 7, 11, 13, 29, 37, 43, 101, 127, 239, 281, 1933, 2689, 4649, 9901, 226549, 459691, 909091, 10838689, 121499449, 4458192223320340849
85  | 3, 3, 41, 271, 2071723, 262533041, 183860605598360013182031235898681337861375354476172692848943767507*
86  | 3, 3, 11, 173, 1527791, 57009401, 67036028520694906474909232998741845465739308829644070493033873920607*
87  | 3, 3, 3, 37, 3191, 4003, 16763, 43037, 62003, 72559, 24144843255794741528557871327290388612558609738573137693751*
88  | 3, 3, 11, 11, 23, 73, 89, 101, 137, 617, 4093, 8779, 21649, 513239, 18028518017591464662285797352193467849325278349388853277*
89  | 3, 3, 497867, 103733951, 215141023324713675098733373868398610805306253288298816088531326692102356683*
90  | 3, 3, 3, 3, 7, 11, 13, 19, 31, 37, 41, 211, 241, 271, 2161, 9091, 29611, 52579, 238681, 333667, 2906161, 3762091, 37609654717775264786067205563121*
91  | 3, 3, 53, 79, 239, 547, 4649, 14197, 17837, 4262077, 265371653, 1524458268957244991673395928413956181993492125406693201*
92  | 3, 3, 11, 47, 101, 139, 1289, 2531, 469229935360571510991280627078200906912258332164075748377095948539772049526983*
93  | 3, 3, 3, 37, 2791, 6943319, 51654427985396940863963963963969129406762503658050360360360360876904640214329769*
94  | 3, 3, 11, 6299, 35121409, 456584607605043405160135308690739341423602592046592719028467123569418033945340911*
95  | 3, 3, 41, 191, 271, 59281, 63841, 1383425490296558584805747650624145731240047611805950403024199892378422499355791*
96  | 3, 3, 3, 7, 11, 13, 17, 37, 73, 97, 101, 137, 353, 449, 641, 1409, 9901, 69857, 206209, 5882353, 99990001, 66554101249, 75118313082913, 9999999900000001
97  | 3, 3, 12004721, 846035731396919233767211537899097169, 109399846855370537540339266842070119107662296580348039
98  | 3, 3, 11, 197, 239, 4649, 909091, 5076142131979746192893401015736040609137060913705583756395939086294416751269035533*
99  | 3, 3, 3, 3, 37, 67, 199, 397, 21649, 34849, 333667, 513239, 487903353968830182288869739868160321618914954114957033410672934232359*

