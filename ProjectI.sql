USE [master]
GO
/****** Object:  Database [ProjectI]    Script Date: 1/5/2021 8:57:27 AM ******/
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
/****** Object:  Table [dbo].[Branch]    Script Date: 1/5/2021 8:57:28 AM ******/
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
/****** Object:  Table [dbo].[Brand]    Script Date: 1/5/2021 8:57:28 AM ******/
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
/****** Object:  Table [dbo].[Customer]    Script Date: 1/5/2021 8:57:28 AM ******/
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
/****** Object:  Table [dbo].[Duty_Roster]    Script Date: 1/5/2021 8:57:28 AM ******/
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
/****** Object:  Table [dbo].[Employee]    Script Date: 1/5/2021 8:57:28 AM ******/
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
/****** Object:  Table [dbo].[Import]    Script Date: 1/5/2021 8:57:28 AM ******/
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
/****** Object:  Table [dbo].[Import_Detail]    Script Date: 1/5/2021 8:57:28 AM ******/
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
/****** Object:  Table [dbo].[Invoice]    Script Date: 1/5/2021 8:57:28 AM ******/
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
/****** Object:  Table [dbo].[Invoice_Detail]    Script Date: 1/5/2021 8:57:28 AM ******/
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
/****** Object:  Table [dbo].[Product]    Script Date: 1/5/2021 8:57:28 AM ******/
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
/****** Object:  Table [dbo].[Revenue]    Script Date: 1/5/2021 8:57:28 AM ******/
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
/****** Object:  Table [dbo].[Salary]    Script Date: 1/5/2021 8:57:28 AM ******/
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
/****** Object:  Table [dbo].[Supplier]    Script Date: 1/5/2021 8:57:28 AM ******/
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
/****** Object:  Table [dbo].[Type]    Script Date: 1/5/2021 8:57:28 AM ******/
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
ALTER TABLE [dbo].[Duty_Roster]  WITH CHECK ADD  CONSTRAINT [FK_Duty_Roster_Employee] FOREIGN KEY([employee_id])
REFERENCES [dbo].[Employee] ([id])
GO
ALTER TABLE [dbo].[Duty_Roster] CHECK CONSTRAINT [FK_Duty_Roster_Employee]
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
