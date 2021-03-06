DROP TABLE IF EXISTS book;

CREATE TABLE book (
  id INT AUTO_INCREMENT PRIMARY KEY,
  bookReference VARCHAR(250) NOT NULL,
  bookTitle VARCHAR(250) NOT NULL,
  bookSummary VARCHAR(600) NOT NULL
);

INSERT INTO book (bookReference, bookTitle, bookSummary) VALUES
  ('BOOK-GRUFF472', 'The Gruffalo', 'A mouse taking a walk in the woods.'),
  ('BOOK-POOH222', 'Winnie The Pooh', 'In this first volume, we meet all the friends from the Hundred Acre Wood.'),
  ('BOOK-WILL987', 'The Wind In The Willows', 'With the arrival of spring and fine weather outside, the good-natured Mole loses patience with spring cleaning. He flees his underground home, emerging to take in the air and ends up at the river, which he has never seen before. Here he meets Rat (a water vole), who at this time of year spends all his days in, on and close by the river. Rat takes Mole for a ride in his rowing boat. They get along well and spend many more days boating, with Rat teaching Mole the ways of the river.');