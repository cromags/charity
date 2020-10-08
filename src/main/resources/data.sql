insert into organization (id, name, description) values
(null, 'Dobry duszek', 'Fundacja niesie pomoc głodnym, biednym i osieroconym dzieciom'),
(null, 'Dzieci Afryki', 'Pomoc osieroconym dzieciom i ubogim rodzinom w Afryce');


insert into good (id, name) values
(null, 'książki'),
(null, 'inne');

insert into donation(id, zip_code, city, address, tel, pick_up_date, pick_up_time, comments, organization_id) values
(1, '01-123', 'Wrocław','Piłsudskiego', '666-555-444','2020-09-01','19:30','uwagi są', 1);

--będzie 6 worków innych(2) i 2 worki książek (1)
insert into donation_details(id, donation_id, good_id, quantity) values
(1, 1, 2, 6),
(2, 1, 1, 2);



