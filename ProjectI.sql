USE [master]
GO
/****** Object:  Database [ProjectI]    Script Date: 1/5/2021 2:10:36 PM ******/
CREATE DATABASE [ProjectI]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'ProjectI', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\ProjectI.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'ProjectI_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\ProjectI_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [ProjectI].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [ProjectI] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [ProjectI] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [ProjectI] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [ProjectI] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [ProjectI] SET ARITHABORT OFF 
GO
ALTER DATABASE [ProjectI] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [ProjectI] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [ProjectI] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [ProjectI] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [ProjectI] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [ProjectI] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [ProjectI] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [ProjectI] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [ProjectI] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [ProjectI] SET  DISABLE_BROKER 
GO
ALTER DATABASE [ProjectI] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [ProjectI] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [ProjectI] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [ProjectI] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [ProjectI] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [ProjectI] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [ProjectI] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [ProjectI] SET RECOVERY FULL 
GO
ALTER DATABASE [ProjectI] SET  MULTI_USER 
GO
ALTER DATABASE [ProjectI] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [ProjectI] SET DB_CHAINING OFF 
GO
ALTER DATABASE [ProjectI] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [ProjectI] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [ProjectI] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'ProjectI', N'ON'
GO
USE [ProjectI]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 1/5/2021 2:10:36 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account](
	[employee_id] [int] NOT NULL,
	[username] [varchar](20) NOT NULL,
	[password] [varchar](20) NOT NULL,
 CONSTRAINT [PK_Account] PRIMARY KEY CLUSTERED 
(
	[employee_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Branch]    Script Date: 1/5/2021 2:10:36 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Branch](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[address] [nvarchar](200) NOT NULL,
	[name] [nvarchar](100) NOT NULL,
	[manager_id] [int] NOT NULL,
 CONSTRAINT [PK_Branch] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Brand]    Script Date: 1/5/2021 2:10:36 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Brand](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](200) NOT NULL,
	[country] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Brand] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Customer]    Script Date: 1/5/2021 2:10:36 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Customer](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NOT NULL,
	[phone] [varchar](20) NOT NULL,
	[address] [nvarchar](200) NULL,
	[point] [int] NULL,
	[email] [varchar](100) NULL,
 CONSTRAINT [PK_Customer] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Duty_Roster]    Script Date: 1/5/2021 2:10:36 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Duty_Roster](
	[employee_id] [int] NOT NULL,
	[date] [date] NOT NULL,
	[total_hour] [float] NOT NULL,
	[note] [text] NOT NULL,
 CONSTRAINT [PK_Duty_Roster] PRIMARY KEY CLUSTERED 
(
	[employee_id] ASC,
	[date] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Employee]    Script Date: 1/5/2021 2:10:36 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Employee](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](100) NOT NULL,
	[dob] [date] NOT NULL,
	[phone] [varchar](20) NOT NULL,
	[address] [nvarchar](200) NOT NULL,
	[start_day] [date] NOT NULL,
	[salary_level] [money] NOT NULL,
	[citizen_id] [varchar](20) NOT NULL,
	[insurance_id] [varchar](30) NOT NULL,
	[role] [int] NOT NULL,
	[branch_id] [int] NOT NULL,
 CONSTRAINT [PK_Employee] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Import]    Script Date: 1/5/2021 2:10:36 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Import](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[supplier_id] [int] NOT NULL,
	[import_date] [date] NOT NULL,
	[total_money] [money] NOT NULL,
 CONSTRAINT [PK_Import] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Import_Detail]    Script Date: 1/5/2021 2:10:36 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Import_Detail](
	[import_id] [int] IDENTITY(1,1) NOT NULL,
	[product_id] [int] NOT NULL,
	[amount] [int] NOT NULL,
	[import_price] [money] NOT NULL,
 CONSTRAINT [PK_Import_Detail] PRIMARY KEY CLUSTERED 
(
	[import_id] ASC,
	[product_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Invoice]    Script Date: 1/5/2021 2:10:36 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Invoice](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[date] [date] NOT NULL,
	[customer_id] [int] NOT NULL,
	[employee_id] [int] NOT NULL,
	[payment_method] [nvarchar](50) NOT NULL,
	[total_money] [money] NOT NULL,
	[tax] [money] NOT NULL,
	[surcharge] [money] NOT NULL,
	[note] [text] NULL,
 CONSTRAINT [PK_Invoice] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Invoice_Detail]    Script Date: 1/5/2021 2:10:36 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Invoice_Detail](
	[invoice_id] [int] NOT NULL,
	[product_id] [int] NOT NULL,
	[import_id] [int] NOT NULL,
	[amount] [int] NOT NULL,
	[retail_price] [money] NOT NULL,
	[discount] [int] NULL,
	[total_money] [money] NOT NULL,
	[import_price] [money] NOT NULL,
 CONSTRAINT [PK_Invoice_Detail] PRIMARY KEY CLUSTERED 
(
	[invoice_id] ASC,
	[product_id] ASC,
	[import_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Product]    Script Date: 1/5/2021 2:10:36 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](200) NOT NULL,
	[description] [nvarchar](1000) NULL,
	[retail_price] [money] NULL,
	[discount] [int] NULL,
	[brand_id] [int] NOT NULL,
	[type_id] [int] NOT NULL,
	[amount] [int] NULL,
 CONSTRAINT [PK_Product] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Revenue]    Script Date: 1/5/2021 2:10:36 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Revenue](
	[branch_id] [int] NOT NULL,
	[month] [int] NOT NULL,
	[year] [int] NOT NULL,
	[total_income] [money] NOT NULL,
	[facilities_fee] [money] NOT NULL,
	[tax] [money] NOT NULL,
	[total_salary] [money] NOT NULL,
	[revenue] [money] NOT NULL,
 CONSTRAINT [PK_Revenue] PRIMARY KEY CLUSTERED 
(
	[branch_id] ASC,
	[month] ASC,
	[year] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Salary]    Script Date: 1/5/2021 2:10:36 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Salary](
	[employee_id] [int] NOT NULL,
	[month] [int] NOT NULL,
	[year] [int] NOT NULL,
	[salary_level] [money] NOT NULL,
	[total_hour] [float] NOT NULL,
	[total_salary] [money] NOT NULL,
 CONSTRAINT [PK_Salary] PRIMARY KEY CLUSTERED 
(
	[employee_id] ASC,
	[month] ASC,
	[year] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Supplier]    Script Date: 1/5/2021 2:10:36 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Supplier](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](200) NOT NULL,
	[address] [nvarchar](300) NOT NULL,
	[phone] [varchar](20) NOT NULL,
 CONSTRAINT [PK_Supplier] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Type]    Script Date: 1/5/2021 2:10:36 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Type](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](100) NOT NULL,
	[description] [nvarchar](1000) NULL,
 CONSTRAINT [PK_Type] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Brand] ON 

INSERT [dbo].[Brand] ([id], [name], [country]) VALUES (1, N'AMD', N'US')
INSERT [dbo].[Brand] ([id], [name], [country]) VALUES (2, N'Intel', N'US')
INSERT [dbo].[Brand] ([id], [name], [country]) VALUES (3, N'Nvidia', N'UK')
INSERT [dbo].[Brand] ([id], [name], [country]) VALUES (4, N'Asus', N'US')
INSERT [dbo].[Brand] ([id], [name], [country]) VALUES (5, N'Gigabyte', N'Germany')
INSERT [dbo].[Brand] ([id], [name], [country]) VALUES (6, N'MSI', N'US')
INSERT [dbo].[Brand] ([id], [name], [country]) VALUES (7, N'HyperX', N'France')
INSERT [dbo].[Brand] ([id], [name], [country]) VALUES (8, N'Gskill', N'US')
INSERT [dbo].[Brand] ([id], [name], [country]) VALUES (9, N'Kingston', N'UK')
INSERT [dbo].[Brand] ([id], [name], [country]) VALUES (10, N'Samsung', N'Korea')
INSERT [dbo].[Brand] ([id], [name], [country]) VALUES (11, N'Cooler Master', N'China')
INSERT [dbo].[Brand] ([id], [name], [country]) VALUES (12, N'Corsair', N'US')
INSERT [dbo].[Brand] ([id], [name], [country]) VALUES (13, N'Xigmatek', N'China')
INSERT [dbo].[Brand] ([id], [name], [country]) VALUES (14, N'LG', N'Korea')
INSERT [dbo].[Brand] ([id], [name], [country]) VALUES (15, N'Western Digital', N'Finland')
INSERT [dbo].[Brand] ([id], [name], [country]) VALUES (16, N'Microsoft', N'US')
INSERT [dbo].[Brand] ([id], [name], [country]) VALUES (17, N'Adobe', N'US')
INSERT [dbo].[Brand] ([id], [name], [country]) VALUES (18, N'Cisco', N'US')
INSERT [dbo].[Brand] ([id], [name], [country]) VALUES (19, N'TP Link', N'Australia')
INSERT [dbo].[Brand] ([id], [name], [country]) VALUES (20, N'Viettel', N'Vietnam')
INSERT [dbo].[Brand] ([id], [name], [country]) VALUES (21, N'Logitech', N'UK')
INSERT [dbo].[Brand] ([id], [name], [country]) VALUES (22, N'Newmen', N'Vietnam')
INSERT [dbo].[Brand] ([id], [name], [country]) VALUES (23, N'Sony', N'Japan')
SET IDENTITY_INSERT [dbo].[Brand] OFF
GO
SET IDENTITY_INSERT [dbo].[Product] ON 

INSERT [dbo].[Product] ([id], [name], [description], [retail_price], [discount], [brand_id], [type_id], [amount]) VALUES (1, N'Ryzen 9 5900x', NULL, NULL, NULL, 1, 1, NULL)
INSERT [dbo].[Product] ([id], [name], [description], [retail_price], [discount], [brand_id], [type_id], [amount]) VALUES (2, N'Ryzen 7 5800x', NULL, NULL, NULL, 1, 1, NULL)
INSERT [dbo].[Product] ([id], [name], [description], [retail_price], [discount], [brand_id], [type_id], [amount]) VALUES (3, N'Ryzen 5 5600x', NULL, NULL, NULL, 1, 1, NULL)
INSERT [dbo].[Product] ([id], [name], [description], [retail_price], [discount], [brand_id], [type_id], [amount]) VALUES (5, N'I7-10700K', NULL, NULL, NULL, 2, 1, NULL)
INSERT [dbo].[Product] ([id], [name], [description], [retail_price], [discount], [brand_id], [type_id], [amount]) VALUES (6, N'I5-10600K', NULL, NULL, NULL, 2, 1, NULL)
INSERT [dbo].[Product] ([id], [name], [description], [retail_price], [discount], [brand_id], [type_id], [amount]) VALUES (7, N'I9-9900K', NULL, NULL, NULL, 2, 1, NULL)
INSERT [dbo].[Product] ([id], [name], [description], [retail_price], [discount], [brand_id], [type_id], [amount]) VALUES (8, N'RTX 3090', NULL, NULL, NULL, 3, 2, NULL)
INSERT [dbo].[Product] ([id], [name], [description], [retail_price], [discount], [brand_id], [type_id], [amount]) VALUES (9, N'RX 6900xt', NULL, NULL, NULL, 1, 2, NULL)
INSERT [dbo].[Product] ([id], [name], [description], [retail_price], [discount], [brand_id], [type_id], [amount]) VALUES (10, N'B550M TUF Plus', NULL, NULL, NULL, 4, 3, NULL)
INSERT [dbo].[Product] ([id], [name], [description], [retail_price], [discount], [brand_id], [type_id], [amount]) VALUES (11, N'B550M Mortar', NULL, NULL, NULL, 6, 3, NULL)
INSERT [dbo].[Product] ([id], [name], [description], [retail_price], [discount], [brand_id], [type_id], [amount]) VALUES (12, N'B550M Aorus Pro', NULL, NULL, NULL, 5, 3, NULL)
INSERT [dbo].[Product] ([id], [name], [description], [retail_price], [discount], [brand_id], [type_id], [amount]) VALUES (13, N'SSD 970 Evo Plus 256GB NVME M.2', NULL, NULL, NULL, 10, 5, NULL)
INSERT [dbo].[Product] ([id], [name], [description], [retail_price], [discount], [brand_id], [type_id], [amount]) VALUES (14, N'SSD 970 Evo Plus 512GB NVME M.2', NULL, NULL, NULL, 10, 5, NULL)
INSERT [dbo].[Product] ([id], [name], [description], [retail_price], [discount], [brand_id], [type_id], [amount]) VALUES (15, N'WD Black 512GB NVME M.2', NULL, NULL, NULL, 15, 5, NULL)
INSERT [dbo].[Product] ([id], [name], [description], [retail_price], [discount], [brand_id], [type_id], [amount]) VALUES (16, N'8GB DDR4 2666Mhz Desktop', NULL, NULL, NULL, 7, 4, NULL)
INSERT [dbo].[Product] ([id], [name], [description], [retail_price], [discount], [brand_id], [type_id], [amount]) VALUES (17, N'16GB DDR4 3200Mhz Desktop', NULL, NULL, NULL, 7, 4, NULL)
INSERT [dbo].[Product] ([id], [name], [description], [retail_price], [discount], [brand_id], [type_id], [amount]) VALUES (18, N'8GB DDR4 3200Mhz Laptop', NULL, NULL, NULL, 10, 4, NULL)
INSERT [dbo].[Product] ([id], [name], [description], [retail_price], [discount], [brand_id], [type_id], [amount]) VALUES (19, N'650W 80 Plus Bronze', NULL, NULL, NULL, 11, 6, NULL)
INSERT [dbo].[Product] ([id], [name], [description], [retail_price], [discount], [brand_id], [type_id], [amount]) VALUES (20, N'1200W 80 Plus Plantinum', NULL, NULL, NULL, 11, 6, NULL)
INSERT [dbo].[Product] ([id], [name], [description], [retail_price], [discount], [brand_id], [type_id], [amount]) VALUES (21, N'850W 80 Plus Gold', NULL, NULL, NULL, 11, 6, NULL)
INSERT [dbo].[Product] ([id], [name], [description], [retail_price], [discount], [brand_id], [type_id], [amount]) VALUES (22, N'I9-10900K', N'', 0.0000, 0, 2, 1, 0)
SET IDENTITY_INSERT [dbo].[Product] OFF
GO
SET IDENTITY_INSERT [dbo].[Supplier] ON 

INSERT [dbo].[Supplier] ([id], [name], [address], [phone]) VALUES (1, N'HUST', N'1 Đại Cồ Việt', N'123456')
INSERT [dbo].[Supplier] ([id], [name], [address], [phone]) VALUES (2, N'HNC', N'2 Đại Cồ Việt', N'145456')
INSERT [dbo].[Supplier] ([id], [name], [address], [phone]) VALUES (3, N'Phong vũ', N'3 Đại Cồ Việt', N'126786')
INSERT [dbo].[Supplier] ([id], [name], [address], [phone]) VALUES (4, N'Mai Hoàng', N'4 Đại Cồ Việt', N'156861')
INSERT [dbo].[Supplier] ([id], [name], [address], [phone]) VALUES (5, N'Nguyễn Công', N'5 Đại Cồ Việt', N'123456')
INSERT [dbo].[Supplier] ([id], [name], [address], [phone]) VALUES (6, N'Gear VN', N'6 Đại Cồ Việt', N'165856')
INSERT [dbo].[Supplier] ([id], [name], [address], [phone]) VALUES (7, N'An Phát', N'7 Đại Cồ Việt', N'879456')
SET IDENTITY_INSERT [dbo].[Supplier] OFF
GO
SET IDENTITY_INSERT [dbo].[Type] ON 

INSERT [dbo].[Type] ([id], [name], [description]) VALUES (1, N'CPU', N'B? x? lý trung tâm')
INSERT [dbo].[Type] ([id], [name], [description]) VALUES (2, N'GPU', N'Card d? h?a')
INSERT [dbo].[Type] ([id], [name], [description]) VALUES (3, N'Main', N'Bo m?ch ch?')
INSERT [dbo].[Type] ([id], [name], [description]) VALUES (4, N'RAM', N'B? nh? trong')
INSERT [dbo].[Type] ([id], [name], [description]) VALUES (5, N'Ổ cứng', N'SSD, HDD...')
INSERT [dbo].[Type] ([id], [name], [description]) VALUES (6, N'PSU', N'Ngu?n')
INSERT [dbo].[Type] ([id], [name], [description]) VALUES (7, N'Case', N'V? máy tính')
INSERT [dbo].[Type] ([id], [name], [description]) VALUES (8, N'Tản nhiệt', N'Qu?t, t?n tháp, t?n nu?c...')
INSERT [dbo].[Type] ([id], [name], [description]) VALUES (9, N'Âm thanh', N'Loa, tai nghe, sound card...')
INSERT [dbo].[Type] ([id], [name], [description]) VALUES (10, N'Gear', N'Bàn phím, chu?t, lót chu?t,...')
INSERT [dbo].[Type] ([id], [name], [description]) VALUES (11, N'Mạng', N'Wifi router, switch, hub, card m?ng,...')
INSERT [dbo].[Type] ([id], [name], [description]) VALUES (12, N'Phần mềm', N'Ph?n m?m b?n quy?n: Win10, Office365, Adobe,...')
INSERT [dbo].[Type] ([id], [name], [description]) VALUES (13, N'Màn hình', N'LCD, LED, OLED...')
SET IDENTITY_INSERT [dbo].[Type] OFF
GO
ALTER TABLE [dbo].[Duty_Roster]  WITH CHECK ADD  CONSTRAINT [FK_Duty_Roster_Employee] FOREIGN KEY([employee_id])
REFERENCES [dbo].[Employee] ([id])
GO
ALTER TABLE [dbo].[Duty_Roster] CHECK CONSTRAINT [FK_Duty_Roster_Employee]
GO
ALTER TABLE [dbo].[Employee]  WITH CHECK ADD  CONSTRAINT [FK_Employee_Account] FOREIGN KEY([id])
REFERENCES [dbo].[Account] ([employee_id])
GO
ALTER TABLE [dbo].[Employee] CHECK CONSTRAINT [FK_Employee_Account]
GO
ALTER TABLE [dbo].[Employee]  WITH CHECK ADD  CONSTRAINT [FK_Employee_Branch] FOREIGN KEY([branch_id])
REFERENCES [dbo].[Branch] ([id])
GO
ALTER TABLE [dbo].[Employee] CHECK CONSTRAINT [FK_Employee_Branch]
GO
ALTER TABLE [dbo].[Import]  WITH CHECK ADD  CONSTRAINT [FK_Import_Supplier] FOREIGN KEY([supplier_id])
REFERENCES [dbo].[Supplier] ([id])
GO
ALTER TABLE [dbo].[Import] CHECK CONSTRAINT [FK_Import_Supplier]
GO
ALTER TABLE [dbo].[Import_Detail]  WITH CHECK ADD  CONSTRAINT [FK_Import_Detail_Import] FOREIGN KEY([import_id])
REFERENCES [dbo].[Import] ([id])
GO
ALTER TABLE [dbo].[Import_Detail] CHECK CONSTRAINT [FK_Import_Detail_Import]
GO
ALTER TABLE [dbo].[Import_Detail]  WITH CHECK ADD  CONSTRAINT [FK_Import_Detail_Product] FOREIGN KEY([product_id])
REFERENCES [dbo].[Product] ([id])
GO
ALTER TABLE [dbo].[Import_Detail] CHECK CONSTRAINT [FK_Import_Detail_Product]
GO
ALTER TABLE [dbo].[Invoice]  WITH CHECK ADD  CONSTRAINT [FK_Invoice_Customer] FOREIGN KEY([customer_id])
REFERENCES [dbo].[Customer] ([id])
GO
ALTER TABLE [dbo].[Invoice] CHECK CONSTRAINT [FK_Invoice_Customer]
GO
ALTER TABLE [dbo].[Invoice]  WITH CHECK ADD  CONSTRAINT [FK_Invoice_Employee] FOREIGN KEY([employee_id])
REFERENCES [dbo].[Employee] ([id])
GO
ALTER TABLE [dbo].[Invoice] CHECK CONSTRAINT [FK_Invoice_Employee]
GO
ALTER TABLE [dbo].[Invoice_Detail]  WITH CHECK ADD  CONSTRAINT [FK_Invoice_Detail_Invoice] FOREIGN KEY([invoice_id])
REFERENCES [dbo].[Invoice] ([id])
GO
ALTER TABLE [dbo].[Invoice_Detail] CHECK CONSTRAINT [FK_Invoice_Detail_Invoice]
GO
ALTER TABLE [dbo].[Invoice_Detail]  WITH CHECK ADD  CONSTRAINT [FK_Invoice_Detail_Product] FOREIGN KEY([product_id])
REFERENCES [dbo].[Product] ([id])
GO
ALTER TABLE [dbo].[Invoice_Detail] CHECK CONSTRAINT [FK_Invoice_Detail_Product]
GO
ALTER TABLE [dbo].[Product]  WITH CHECK ADD  CONSTRAINT [FK_Product_Brand] FOREIGN KEY([brand_id])
REFERENCES [dbo].[Brand] ([id])
GO
ALTER TABLE [dbo].[Product] CHECK CONSTRAINT [FK_Product_Brand]
GO
ALTER TABLE [dbo].[Product]  WITH CHECK ADD  CONSTRAINT [FK_Product_Type] FOREIGN KEY([type_id])
REFERENCES [dbo].[Type] ([id])
GO
ALTER TABLE [dbo].[Product] CHECK CONSTRAINT [FK_Product_Type]
GO
ALTER TABLE [dbo].[Revenue]  WITH CHECK ADD  CONSTRAINT [FK_Revenue_Branch] FOREIGN KEY([branch_id])
REFERENCES [dbo].[Branch] ([id])
GO
ALTER TABLE [dbo].[Revenue] CHECK CONSTRAINT [FK_Revenue_Branch]
GO
ALTER TABLE [dbo].[Salary]  WITH CHECK ADD  CONSTRAINT [FK_Salary_Employee] FOREIGN KEY([employee_id])
REFERENCES [dbo].[Employee] ([id])
GO
ALTER TABLE [dbo].[Salary] CHECK CONSTRAINT [FK_Salary_Employee]
GO
USE [master]
GO
ALTER DATABASE [ProjectI] SET  READ_WRITE 
GO
