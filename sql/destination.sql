-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  ven. 29 nov. 2019 à 11:28
-- Version du serveur :  10.4.8-MariaDB
-- Version de PHP :  7.1.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `mobiliti_bdd`
--

-- --------------------------------------------------------

--
-- Structure de la table `destination`
--

CREATE TABLE `destination` (
  `id_destination` int(11) NOT NULL,
  `Nom` varchar(100) NOT NULL,
  `Ville` varchar(100) NOT NULL,
  `Pays` varchar(100) NOT NULL,
  `Description` varchar(500) NOT NULL,
  `Domaine` varchar(100) NOT NULL,
  `Nombre_de_place` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `destination`
--

INSERT INTO `destination` (`id_destination`, `Nom`, `Ville`, `Pays`, `Description`, `Domaine`, `Nombre_de_place`) VALUES
(3, 'TECHNISCHE UNIVERSITAT CLAUSTHAL', 'OBERHARZ', 'ALLEMAGNE', 'L\'universite de technologie de Clausthal, implantee depuis 1775 a Clausthal-Zellerfeld, en Basse-Saxe, est l\'une des plus petites universites d\'Allemagne.', 'ITI', 2),
(4, 'ICAI ', 'MADRID', 'ESPAGNE', 'La tradition de l\'ecole d\'ingenieur ICAI dans l\'enseignement superieur commence a Madrid en 1908. Actuellement, l\'ICAI est une ecole polytechnique qui se divise en deux grands domaines: l\'ingenierie industrielle (y compris mecanique, electrique et electronique) et l\'ingenierie des telecommunications .', 'ITI', 2),
(5, 'GEBZE INSTITUTE OF TECHNOLOGY', 'GEBZE', 'TURQUIE', 'Gebze Technical University (Turkish: Gebze Teknik Üniversitesi (GTÜ)), formerly known as Gebze Institute of Technology (GIT), is one of two institutes of technology founded in Turkey', 'ITI', 2),
(6, 'UNIVERSIDADE DE SANTA CATARINA', 'SAO JOSE', 'BRESIL', 'L\'universite federale de Santa Catarina (Universidade Federal de Santa Catarina ou UFSC) est une universite federale bresilienne situee principalement a Florianopolis, capitale de l\'Etat de Santa Catarina.\r\nElle est reconnue parmi les meilleures universites du Bresil, notamment dans le domaine du genie mecanique, du genie electrique et du journalisme, qui sont des references au niveau national.\r\n', 'BTP', 2),
(7, 'XIJING UNIVERSITY', 'XI\'AN', 'CHINE', 'Situated in the university town in the south of Xi\'an,one of ancient capitals in China, Xijing University is a non-governmental institution of higher learning that is qualified to conduct post-graduate education. In 2005, it developed into a university from a previous vocational college and was named\" Xijing University\". In 2009, it was permitted to grant bachelor\'s degree. In 2011, the university further obtained the qualification for carrying out post-graduate education on professional degree.', 'BTP', 3),
(8, 'VILNIUS GEDIMINAS TECHNICAL UNIVERSITY', 'VILNIUS', 'LITUANIE', 'Vilnius Gediminas Technical University (VGTU) is an innovative Lithuanian University, educating highly qualified and creative specialists. The University is the leader among the institutions of technological science education, ensuring modern studies, orientated to the labour market.', 'CM', 1),
(9, 'UNIVERSITY OF TULSA', 'TULSA', 'USA', 'University of Tulsa is a private institution that was founded in 1894. It has a total undergraduate enrollment of 3,296, its setting is city, and the campus size is 209 acres. It utilizes a semester-based academic calendar. University of Tulsa\'s ranking in the 2020 edition of Best Colleges is National Universities, #121. Its tuition and fees are $42,238.', 'CM', 1),
(10, 'BFH BERNER FACHHOCHSHULE', 'BERN', 'SUISSE', 'Founded in 1997, Berner Fachhochschule (Bern University of Applied Sciences) is a non-profit public higher education institution located in the large town of Bern (population range of 50,000-249,999 inhabitants). This institution has also branch campuses in the following location(s): Biel, Burgdorf, Magglingen, Zollikofen. Officially accredited and/or recognized by the Staatssekretariat für Bildung, Forschung und Innovation, Schweiz (State Secretariat for Education, Research and Innovation, Swit', 'CM', 1),
(11, 'DE LA SALLE UNIVERSITY', 'MANILA', 'PHILIPPINES', 'De La Salle University positions itself as a leader in molding human resources who serve the church and the nation. It is a Catholic coeducational institution founded in 1911 by the Brothers of the Christian Schools. The University is a hub for higher education training renowned for its academic excellence, prolific and relevant research, and involved community service.\r\n\r\nNestled in the heart of Manila, De La Salle University is home to local and international students seeking quality education', 'CM', 2),
(12, '2iE', 'OUAGADOUGOU', 'BURKINA FASO', 'L\'Institut 2iE est un centre d\'enseignement superieur et de recherche membre de la Conference des grandes ecoles (CGE)2 et base au Burkina Faso. Specialises dans les domaines de l\'eau, de l\'energie, de l\'environnement, du genie civil et des mines, ses diplomes d\'ingenieur ont recu l\'accreditation de la Commission francaise des Titres d\'Ingenieur (CTI) ce qui leur octroie une reconnaissance europeenne a travers le label EUR-ACE.', 'ESEA', 2),
(13, 'KING\'S COLLEGE', 'LONDRES', 'ANGLETERRE', 'King\'s College London was founded by King George IV and the Duke of Wellington (then Prime Minister) in 1829 as a university college in the tradition of the Church of England. It now welcomes staff and students of all faiths and beliefs.\r\n\r\nKing\'s professors played a major part in nineteenth-century science and in extending higher education to women and working men through evening classes.', 'ESEA', 1),
(14, 'UNIVERSIDAD VALLADOLID', 'VALLADOLID', 'ESPAGNE', 'L\'universite de Valladolid est une universite publique espagnole fondee au xiiie siecle. Elle est chargee de l\'education superieure de quatre provinces de la communaute de Castille-et-Leon (province de Valladolid, province de Palencia, province de Soria et province de Segovie, les principaux etablissements se situant dans les capitales de ces provinces portant le meme nom).', 'ESEA', 2),
(15, 'INSTITUTO POLITECNICO DE COIMBRA', 'COIMBRA', 'PORTUGAL', 'By signing “Scientiae Thesaurus Mirabilis”, King Dinis created the oldest university of the country and one of the oldest in the world. Dated from 1290, the document originated the General Study, recognised in the same year by Pope Nicholas IV. A century after the birth of the nation, the University of Coimbra germinated. It starts operating in Lisbon but, in 1308, it is transferred to Coimbra, alternating between the two cities until 1537, when it is definitely settled in the city of river Mond', 'ESEA', 2),
(16, 'UNIVERSITE DU QUEBEC ', 'CHICOUTIMI', 'CANADA', 'Fondee en 1969, l\'Universite du Quebec a Chicoutimi fait partie du plus grand reseau universitaire du Canada, celui de l\'Universite du Quebec. Elle est situee au coeur du Saguenay?Lac-Saint-Jean, une region francophone reconnue pour la beaute de son fjord, et possede quatre centres d\'etudes universitaires ainsi qu\'une ecole du numerique. Forte du succes de ses 57 000 diplomes, l\'UQAC accueille chaque annee 6 500 etudiants, dont plus de 1 000 sont issus d\'une cinquantaine de pays a travers le mon..', 'ESEA', 2),
(18, 'SOPHIA UNIVERSITY', 'TOKYO', 'JAPON', 'Sophia University is a leading private research university in Japan, with its main campus located near Yotsuya station, in Central Tokyo\'s Chiyoda Ward. It takes its name from the Greek Sophia meaning \"wisdom\". The Japanese name, J?chi Daigaku, literally means \"University of Higher Wisdom\".', 'ESEA', 2),
(19, 'TECHNICAL UNIVERSITY OF LODZ', 'LODZ', 'POLOGNE', 'The Lodz University of Technology with the Faculty of Mechanical Engineering was founded by the state decree of 24 May 1945.\r\n\r\nSixteen professors took part in the first  meeting of the Mechanical Faculty Council.\r\n\r\nAt present the Faculty staff consists of over 200 academics, including 25 professors and 30 associate professors.\r\n\r\n ', 'IMS', 1),
(20, 'MARQUETTE UNIVERSITY', 'MILWAUKEE', 'USA', 'Marquette University is a Catholic, Jesuit university located near the heart of downtown Milwaukee, Wis., that offers a comprehensive range of majors in 11 nationally and internationally recognized colleges and schools.\r\n\r\nA Marquette education offers students a virtually unlimited number of paths and destinations and prepares them for the world by asking them to think critically about it.\r\n\r\nAlong the way, we ask one thing of every student: Be The Difference.', 'IMS', 2),
(21, 'INHA', 'INCHEON', 'COREE', 'Founded in 1954 to fulfill the dearest wish of empowering and bringing prosperity to the nation, Inha University stands for the Ideals of building students\' characters, searching for the truth and serving society in order to embody the University\'s founding principles of creativity, diligence and service.\r\n\r\n“Truth” the school’s motto, indicates we as a research university endeavor to contribute to improving the nation as well as all human society, to embark on great ventures into the world, and', 'BAA', 3),
(22, 'MEIJI UNIVERSITY', 'TOKYO', 'JAPON', 'Meiji University was founded in January 1881 as the Meiji Law School by a group of young lawyers barely in their 30\'s: Tatsuo Kishimoto, Kozo Miyagi, and Misao Yashiro. It was an era characterized by Japan\'s urgent need to develop as a modern independent nation. The three founded the Meiji Law School in their fervent hope to \"foster bright capable youths who would lead a modern civil society in Japan\".', 'BAA', 2),
(23, 'PAMUKKALE UNIVERSITY', 'PAMUKKALE', 'TURQUIE', 'The faculty, which started to teach as Female Teacher Training School in 1957 in Denizli and then turned into Education Institute in 1976, was attached to Dokuz Eylül University Buca Faculty of Education under the name of Denizli Education College with Decree no. 41 on 20.07.1982. In 1989, it started to deliver four-year undergraduate education and on November 10, 1992 it became the Pamukkale University Faculty of Education.\r\n\r\n', 'BAA', 2),
(24, 'UNIVERSITA DEGLI STUDI DI TRIESTE', 'TRIESTE', 'ITALIE', 'The University of Trieste (Italian: Università degli Studi di Trieste, or UniTS) is a public research university in Trieste in the Friuli-Venezia Giulia region in northeast Italy. The university consists of 10 departments, boasts a wide and almost complete range of university courses and has about 15,000 students and 1,000 professors. It was founded in 1924.\r\n\r\nThe historical international vocation of the University of Trieste is witnessed by its intense and high-level activity: Trieste is the c', 'BAA', 1),
(25, 'UNIVERSIDAD MAYOR', 'SANTIAGO', 'CHILI', 'Universidad Mayor is a private university in Santiago, Chile. It was founded in 1988. Universidad Mayor opened its first academic programs in 1988 with bachelor\'s degrees in Architecture and Engineering with 3 majors; Industrial Engineering, Computer Science and Electronics Engineering which were offered at its Main Campus in Américo Vespucio.', 'BAA', 1),
(26, 'IBEROAMERICANA', 'MEXICO', 'MEXIQUE', 'The Ibero-American University (in Spanish: Universidad Iberoamericana, abbreviated UIA but commonly known as Ibero or La Ibero) is one of the most prestigious universities in Mexico and in Latin America. The private institution of higher education is sponsored by the Society of Jesus, and it is recognized as having an international-grade level of excellence. In 2009, the UIA received the SEP-ANUIES Prize as the best private university in Mexico. The Ibero\'s flagship campus is located in the Sant', 'PCE', 1),
(27, 'UCP PRAGUE- INSTITUTE OF CHEMICAL TECHNOLOGY', 'PRAGUE', 'RÉPUBLIQUE TCHEQUE', 'The Institute of Chemical Technology in Prague (ICT) is the biggest educational institution of its kind in Central Europe. The school is almost 200 years old and its tradition combined with progressive fields of study allows every student to get in touch with advanced technologies. The Institute of Chemical Technology in Prague thanks to its international reputation together with the good educational quality offers a prospect of a prestigious and highly remunerative professional career both in t', 'PCE', 2),
(28, 'HOSCHSCHULE NIEDERRHEIN - KREFELD', 'KREFELD', 'ALLEMAGNE', 'Die Hochschule Niederrhein ist mit derzeit 14.500 Studierenden eine der grossten und leistungsfahigsten Hochschulen fur Angewandte Wissenschaften Deutschlands mit Standorten in Krefeld und Monchengladbach. Unser Profil in Lehre und Forschung verbindet Technik und Gesellschaft.\r\n\r\nMit uber 80 Bachelor- und Masterstudiengangen eroffnen wir jungen Menschen Perspektiven. Dank einer problem- und transferorientierten Forschung sind wir innovative Impulsgeber fur die Unternehmen der Region.', 'PCE', 2),
(29, 'UNIVERSIDAD DE SALAMANCA - FACULTAT DE CIENCIAS QUIMICAS', 'SALAMANCA', 'ESPAGNE', 'The University of Salamanca (Spanish: Universidad de Salamanca) is a Spanish higher education institution, located in the city of Salamanca, west of Madrid, in the autonomous community of Castile and León. It was founded in 1134 and given the Royal charter of foundation by King Alfonso IX in 1218. It is the oldest university in the Hispanic world and the third oldest university in the entire world still in operation. The formal title of \"University\" was granted by King Alfonso X in 1254 and reco', 'PCE', 1),
(30, 'QUEENSLAND UNIVERSITY OF TECHNOLOGY', 'BRISBANE', 'AUSTRALIE', 'L\'Universite de technologie du Queensland (QUT) est une grande universite australienne avec une vision mondiale et une vision du monde reel. Nous sommes l\'une des universites de recherche a la croissance la plus rapide du pays et nos cours sont en forte demande. Nos diplomes comprennent huit boursiers Rhodes, dont cinq ont ete recompenses au cours des six dernieres annees.\r\n\r\nNous sommes une institution ambitieuse et collaborative qui cherche a doter nos etudiants et nos diplomes des competences', 'SMART CITIES', 1),
(31, 'UNIVERSITE POLYTECHNIQUE DE SAINT PETERSBOURG', 'SAINT-PETERSBOURG', 'RUSSIE', 'SPbPU is the oldest technical university in Russia and is also a national research university.   The university\'s graduates are in demand across research, design and business.   SPbPU offers its students: A wide selection of study programmes (Bachelor\'s, Specialist and Master\'s Degrees, Postgraduate Studies Programme); Teaching from the best lecturers of St. Petersburg and European universities;  Internships;  Academic mobility programmes;  Double degree programmes;  Scholarships;  Assistance ga', 'SMART CITIES', 2),
(32, 'DUBLIN INSTITUTE OF TECHNOLOGY', 'DUBLIN', 'IRLANDE', 'Technological University Dublin (Irish: Ollscoil Teicneolaiochta Bhaile Atha Cliath) or TU Dublin is Ireland\'s first technological university, established on 1 January 2019, taking over all functions and operations of the three preceding institutions.\r\n\r\nThe university was formed by the amalgamation of three existing institutes of technology in the Dublin area - Dublin Institute of Technology, Institute of Technology, Blanchardstown, and Institute of Technology, Tallaght.', 'SMART CITIES', 1);


-- Index pour les tables déchargées
--

--
-- Index pour la table `destination`
--
ALTER TABLE `destination`
  ADD PRIMARY KEY (`id_destination`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `destination`
--
ALTER TABLE `destination`
  MODIFY `id_destination` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
