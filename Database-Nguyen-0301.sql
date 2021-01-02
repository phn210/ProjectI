use ProjectI

insert into Type(name, description)
values (N'CPU', N'Bộ xử lý trung tâm'),
	(N'GPU', N'Card đồ họa'),
	(N'Main', N'Bo mạch chủ'),
	(N'RAM', N'Bộ nhớ trong'),
	(N'Ổ cứng', N'SSD, HDD...'),
	(N'PSU', N'Nguồn'),
	(N'Case', N'Vỏ máy tính'),
	(N'Tản nhiệt', N'Quạt, tản tháp, tản nước...'),
	(N'Âm thanh', N'Loa, tai nghe, sound card...'),
	(N'Gear', N'Bàn phím, chuột, lót chuột,...'),
	(N'Mạng', N'Wifi router, switch, hub, card mạng,...'),
	(N'Phần mềm', N'Phần mềm bản quyền: Win10, Office365, Adobe,...'),
	(N'Màn hình', N'LCD, LED, OLED...')

insert into Supplier(name, address, phone)
values (N'HUST', N'1 Đại Cồ Việt', '123456'),
	(N'HNC', N'2 Đại Cồ Việt', '145456'),
	(N'Phong vũ', N'3 Đại Cồ Việt', '126786'),
	(N'Mai Hoàng', N'4 Đại Cồ Việt', '156861'),
	(N'Nguyễn Công', N'5 Đại Cồ Việt', '123456'),
	(N'Gear VN', N'6 Đại Cồ Việt', '165856'),
	(N'An Phát', N'7 Đại Cồ Việt', '879456')

insert into Brand(name, country)
values (N'AMD', N'US'),
	(N'Intel', N'US'),
	(N'Nvidia', N'UK'),
	(N'Asus', N'US'),
	(N'Gigabyte', N'Germany'),
	(N'MSI', N'US'),
	(N'HyperX', N'France'),
	(N'Gskill', N'US'),
	(N'Kingston', N'UK'),
	(N'Samsung', N'Korea'),
	(N'Cooler Master', N'China'),
	(N'Corsair', N'US'),
	(N'Xigmatek', N'China'),
	(N'LG', N'Korea'),
	(N'Western Digital', N'Finland'),
	(N'Microsoft', N'US'),
	(N'Adobe', N'US'),
	(N'Cisco', N'US'),
	(N'TP Link', N'Australia'),
	(N'Viettel', N'Vietnam'),
	(N'Logitech', N'UK'),
	(N'Newmen', N'Vietnam'),
	(N'Sony', N'Japan')
