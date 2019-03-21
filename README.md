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
38  | 3, 3, 11, 909090909090909091, 1111111111111111111
39  | 3, 3, 3, 37, 53, 79, 265371653, 900900900900990990990991
40  | 3, 3, 11, 41, 73, 101, 137, 271, 3541, 9091, 27961, 1676321, 5964848081
41  | 3, 3, 83, 1231, 538987, 201763709900322803748657942361
42  | 3, 3, 3, 7, 7, 11, 13, 37, 43, 127, 239, 1933, 2689, 4649, 459691, 909091, 10838689
43  | 3, 3, 173, 1527791, 1963506722254397, 2140992015395526641
44  | 3, 3, 11, 11, 23, 89, 101, 4093, 8779, 21649, 513239, 1052788969, 1056689261
45  | 3, 3, 3, 3, 31, 37, 41, 271, 238681, 333667, 2906161, 4185502830133110721
46  | 3, 3, 11, 47, 139, 2531, 549797184491917, 11111111111111111111111
47  | 3, 3, 35121409, 316362908763458525001406154038726382279
48  | 3, 3, 3, 7, 11, 13, 17, 37, 73, 101, 137, 9901, 5882353, 99990001, 9999999900000001
49  | 3, 3, 239, 4649, 505885997, 1976730144598190963568023014679333
50  | 3, 3, 11, 41, 251, 271, 5051, 9091, 21401, 25601, 182521213001, 78875943472201
51  | 3, 3, 3, 37, 613, 210631, 2071723, 52986961, 5363222357, 13168164561429877
52  | 3, 3, 11, 53, 79, 101, 521, 859, 265371653, 1058313049, 1900381976777332243781
53  | 3, 3, 107, 1659431, 1325815267337711173, 47198858799491425660200071
54  | 3, 3, 3, 3, 3, 7, 11, 13, 19, 37, 757, 52579, 333667, 70541929, 14175966169, 440334654777631
55  | 3, 3, 41, 271, 1321, 21649, 62921, 513239, 83251631, 1300635692678058358830121
56  | 3, 3, 11, 29, 73, 101, 137, 239, 281, 4649, 7841, 909091, 121499449, 127522001020150503761
57  | 3, 3, 3, 37, 21319, 10749631, 1111111111111111111, 3931123022305129377976519
58  | 3, 3, 11, 59, 3191, 16763, 43037, 62003, 77843839397, 154083204930662557781201849
59  | 3, 3, 2559647034361, 4340876285657460212144534289928559826755746751
60  | 3, 3, 3, 7, 11, 13, 31, 37, 41, 61, 101, 211, 241, 271, 2161, 3541, 9091, 9901, 27961, 2906161, 4188901, 39526741
61  | 3, 3, 733, 4637, 329401, 974293, 1360682471, 106007173861643, 7061709990156159479
62  | 3, 3, 11, 2791, 6943319, 57336415063790604359, 909090909090909090909090909091
63  | 3, 3, 3, 3, 37, 43, 239, 1933, 4649, 10837, 23311, 45613, 333667, 10838689, 45121231, 1921436048294281
64  | 3, 3, 11, 17, 73, 101, 137, 353, 449, 641, 1409, 19841, 69857, 976193, 5882353, 6187457, 834427406578561
65  | 3, 3, 41, 53, 79, 271, 265371653, 162503518711, 5538396997364024056286510640780600481
66  | 3, 3, 3, 7, 11, 11, 13, 23, 37, 67, 4093, 8779, 21649, 513239, 599144041, 183411838171, 1344628210313298373
67  | 3, 3, 493121, 79863595778924342083, 28213380943176667001263153660999177245677
68  | 3, 3, 11, 101, 103, 4013, 2071723, 28559389, 1491383821, 5363222357, 21993833369, 2324557465671829
69  | 3, 3, 3, 37, 277, 203864078068831, 11111111111111111111111, 1595352086329224644348978893
70  | 3, 3, 11, 41, 71, 239, 271, 4649, 9091, 123551, 909091, 4147571, 102598800232111471, 265212793249617641
71  | 3, 3, 241573142393627673576957439049, 45994811347886846310221728895223034301839
72  | 3, 3, 3, 3, 7, 11, 13, 19, 37, 73, 101, 137, 3169, 9901, 52579, 98641, 333667, 99990001, 999999000001, 3199044596370769
73  | 3, 3, 12171337159, 1855193842151350117, 49207341634646326934001739482502131487446637
74  | 3, 3, 11, 7253, 2028119, 247629013, 422650073734453, 296557347313446299, 2212394296770203368013
75  | 3, 3, 3, 31, 37, 41, 151, 271, 4201, 21401, 25601, 2906161, 182521213001, 15763985553739191709164170940063151
76  | 3, 3, 11, 101, 722817036322379041, 909090909090909091, 1111111111111111111, 1369778187490592461
77  | 3, 3, 239, 4649, 5237, 21649, 42043, 513239, 29920507, 136614668576002329371496447555915740910181043
78  | 3, 3, 3, 7, 11, 13, 13, 37, 53, 79, 157, 859, 6397, 216451, 265371653, 1058313049, 388847808493, 900900900900990990990991
79  | 3, 3, 317, 6163, 10271, 307627, 49172195536083790769, 3660574762725521461527140564875080461079917
80  | 3, 3, 11, 17, 41, 73, 101, 137, 271, 3541, 9091, 27961, 1676321, 5070721, 5882353, 5964848081, 19721061166646717498359681
81  | 3, 3, 3, 3, 3, 3, 37, 163, 757, 9397, 333667, 2462401, 440334654777631, 676421558270641, 130654897808007778425046117
82  | 3, 3, 11, 83, 1231, 538987, 2670502781396266997, 3404193829806058997303, 201763709900322803748657942361
83  | 3, 3, 3367147378267, 9512538508624154373682136329, 346895716385857804544741137394505425384477
84  | 3, 3, 3, 7, 7, 11, 13, 29, 37, 43, 101, 127, 239, 281, 1933, 2689, 4649, 9901, 226549, 459691, 909091, 10838689, 121499449, 4458192223320340849
85  | 3, 3, 41, 271, 2071723, 262533041,  5363222357, 8119594779271, 4222100119405530170179331190291488789678081
86  | 3, 3, 11, 173, 1527791, 57009401, 2182600451, 1963506722254397, 2140992015395526641, 7306116556571817748755241
87  | 3, 3, 3, 37, 3191, 4003, 16763, 43037, 62003, 72559, 77843839397, 310170251658029759045157793237339498342763245483
88  | 3, 3, 11, 11, 23, 73, 89, 101, 137, 617, 4093, 8779, 21649, 513239, 1052788969, 1056689261, 16205834846012967584927082656402106953
89  | 3, 3, 497867, 103733951, 104984505733, 5078554966026315671444089, 403513310222809053284932818475878953159
90  | 3, 3, 3, 3, 7, 11, 13, 19, 31, 37, 41, 211, 241, 271, 2161, 9091, 29611, 52579, 238681, 333667, 2906161, 3762091, 8985695684401, 4185502830133110721
91  | 3, 3, 53, 79, 239, 547, 4649, 14197, 17837, 4262077, 265371653, 43442141653, 316877365766624209, 110742186470530054291318013 
92  | 3, 3, 11, 47, 101, 139, 1289, 2531, 18371524594609, 549797184491917, 11111111111111111111111, 4181003300071669867932658901
93  | 3, 3, 3, 37, 2791, 6943319, 57336415063790604359, 900900900900900900900900900900990990990990990990990990990991  
94  | 3, 3, 11, 6299, 35121409, 4855067598095567, 297262705009139006771611927, 316362908763458525001406154038726382279
95  | 3, 3, 41, 191, 271, 59281, 63841, 1111111111111111111, 1289981231950849543985493631, 965194617121640791456070347951751
96  | 3, 3, 3, 7, 11, 13, 17, 37, 73, 97, 101, 137, 353, 449, 641, 1409, 9901, 69857, 206209, 5882353, 99990001, 66554101249, 75118313082913, 9999999900000001
97  | 3, 3, 12004721, 846035731396919233767211537899097169, 109399846855370537540339266842070119107662296580348039
98  | 3, 3, 11, 197, 239, 4649, 909091,  505885997, 1976730144598190963568023014679333, 5076141624365532994918781726395939035533
99  | 3, 3, 3, 3, 37, 67, 199, 397, 21649, 34849, 333667, 513239, 1344628210313298373, 362853724342990469324766235474268869786311886053883
