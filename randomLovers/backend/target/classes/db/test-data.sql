DELETE FROM comment;
DELETE FROM user;
DELETE FROM topic;
INSERT INTO user(ID, USERNAME, PASSWORD) VALUES
(1, 'anacprocha', 'ac2021'),
(2, 'zemaria', 'ac2021'),
(3, 'catpereira', 'ac2021'),
(4, 'diiipinto', 'ac2021'),
(5, 'megoo', 'ac2021');

INSERT INTO topic(ID, NAME, OPTIONANAME, OPTIONA, OPTIONBNAME, OPTIONB, TOTALVOTES, IMAGEPATH, DESCRIPTION) VALUES
(1, 'Pineapple Pizza', 'Pizza with pineapple', 33, 'Pizza without pineapple', 33, 60, 'resources/images/pineapple-pizza.jpg', 'Serious talk. Pineapple on pizza?'),
(2, 'Socks with Shoes', 'Sock Sock. Shoe Shoe', 34, 'Sock Shoe. Sock Shoe', 34, 60, 'resources/images/sock-shoe.jpg', 'You´re getting ready in the morning, in which order do you put your socks and shoes?'),
(3, 'Cereals and Milk', 'Cereals first', 33, 'Milk first', 33, 60, 'resources/images/cereals-milk.jpg', 'Cereals or milk, which goes first in the bowl?'),
(4, 'Family Tree', 'Siblings', 50, 'Not Siblings', 30, 80, 'resources/images/siblings.jpg', 'Are these two random people related?'),
(5, 'Carneiro', 'Cordeiro', 40, 'Caseiro', 40, 80, 'resources/images/carneiro.jpg', 'Whats this persons real name?'),
(6, 'Who came first', 'Chicken', 33, 'Egg', 33, 60, 'resources/images/chicken-egg.jpg', 'Whose '),
(7, 'Black and White', 'White with black stripes', 33, 'Black with white stripes', 33, 60, 'resources/images/zebra.jpg', 'Description'),
(8, 'Clap clap for Igreja', 'Clap Clap', 33, 'Enjoy the Silence', 33, 60, 'resources/images/igreja.jpg', 'Description'),
(9, 'Soda Wars', 'Coke', 44, 'Pepsi', 36, 80, 'resources/images/soda.jpg', 'Lets keep it simple. Coke or Pepsi?'),
(10, 'Kitchenware', 'Cada panela com o seu testo', 34, 'Cada testo uma panela', 34, 60, 'resources/images/kitchenware.jpg', 'What´s the popular portuguese saying?'),
(11, 'Stream and Chill', 'Netflix', 34, 'HBO', 34, 60, 'resources/images/stream.jpg', 'How do you like to chill?'),
(12, 'TV Hosts', 'Cristina Ferreira', 11, 'Goucha', 69, 80, 'resources/images/tv-host.jpg', 'Whos the best tv host in tv history?'),
(13, 'GOAT', 'Ronaldo', 80, 'Messi', 0, 80, 'resources/images/goat.jpg', 'Greatest Of All Time.'),
(14, 'Best Repo', 'GitLab', 34, 'GitHub', 34, 60, 'resources/images/repo.jpg', 'If you had to choose, which is the best?'),
(15, 'Txt Editor', 'IntelliJ', 0, 'Vs Code', 0, 80, 'resources/images/txt-editor.jpg', 'What´s the best way to do some lines?');

INSERT INTO comment(ID, NUMBERLIKES, NUMBERDISLIKES, TOTALPOINTS, USER_ID, TOPIC_ID, CONTENT)VALUES
(1, 10, 3, 7, 1,1,'People that can´t accept pineapple on pizza are just stupid. Can´t understand that shit'),
(2, 10, 3, 7, 1,9,'Coke all the way #CokeAddict'),
(3, 10, 3, 7, 2,4,'OMG!! Such cute twins!! Can´t believe the similarities!!'),
(4, 10, 3, 7, 3,5,'Caseiro! No doubts!' ),
(5, 10, 3, 7, 4,13,'CR7! Love from Turkey, come to Besiktas! :D'),
(6, 10, 3, 7, 5,1,'How can this person say that pineapple on pizza is a great choice? Oh lord please send help from above.'),
(10, 10, 3, 7, 1,15,'Me too, I easily sniff them all the time.'),
(8, 10, 3, 7, 2,4,'Seriously dude, no way in hell they are related??'),
(9, 10, 3, 7, 3,12,'OMG!! I just love Goucha so much!! Can´t imagine my life without him!!'),
(7, 10, 3, 7, 4,15,'I just love IntelliJ!!!! Its so easy to sniff the bugs!!!!');
















