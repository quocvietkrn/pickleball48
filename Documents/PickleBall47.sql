USE [master]
GO
/****** Object:  Database [PickleBall48]    Script Date: 2/25/2025 9:57:02 AM ******/
CREATE DATABASE [PickleBall48]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'PickleBall48', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\PickleBall48.mdf' , SIZE = 73728KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'PickleBall48_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\PickleBall48_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [PickleBall48] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [PickleBall48].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [PickleBall48] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [PickleBall48] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [PickleBall48] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [PickleBall48] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [PickleBall48] SET ARITHABORT OFF 
GO
ALTER DATABASE [PickleBall48] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [PickleBall48] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [PickleBall48] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [PickleBall48] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [PickleBall48] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [PickleBall48] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [PickleBall48] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [PickleBall48] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [PickleBall48] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [PickleBall48] SET  ENABLE_BROKER 
GO
ALTER DATABASE [PickleBall48] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [PickleBall48] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [PickleBall48] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [PickleBall48] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [PickleBall48] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [PickleBall48] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [PickleBall48] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [PickleBall48] SET RECOVERY FULL 
GO
ALTER DATABASE [PickleBall48] SET  MULTI_USER 
GO
ALTER DATABASE [PickleBall48] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [PickleBall48] SET DB_CHAINING OFF 
GO
ALTER DATABASE [PickleBall48] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [PickleBall48] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [PickleBall48] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [PickleBall48] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'PickleBall48', N'ON'
GO
ALTER DATABASE [PickleBall48] SET QUERY_STORE = ON
GO
ALTER DATABASE [PickleBall48] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [PickleBall48]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 2/25/2025 9:57:02 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account](
	[IDAccount] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](100) NULL,
	[UserName] [nvarchar](100) NULL,
	[PassWord] [nvarchar](100) NULL,
	[Gender] [int] NULL,
	[PhoneNumber] [nvarchar](20) NULL,
	[IDEmail] [nvarchar](50) NULL,
	[IDFacebook] [nvarchar](50) NULL,
	[Bank] [nvarchar](50) NULL,
	[BankNumber] [nvarchar](20) NULL,
	[Role] [int] NULL,
	[Status] [int] NULL,
	[Dob] [date] NULL,
PRIMARY KEY CLUSTERED 
(
	[IDAccount] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Bill]    Script Date: 2/25/2025 9:57:02 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Bill](
	[IDBill] [int] IDENTITY(1,1) NOT NULL,
	[Invoice] [nvarchar](50) NULL,
	[IDRegisteredPickleBallField] [int] NULL,
	[IDAccount2] [int] NULL,
	[PaymentDate] [smalldatetime] NULL,
	[TotalPrice] [money] NULL,
PRIMARY KEY CLUSTERED 
(
	[IDBill] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Order_Details]    Script Date: 2/25/2025 9:57:02 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Order_Details](
	[order_detail_id] [int] IDENTITY(1,1) NOT NULL,
	[order_id] [int] NOT NULL,
	[product_id] [int] NOT NULL,
	[quantity] [int] NOT NULL,
	[price] [decimal](10, 2) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[order_detail_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Order_Payments]    Script Date: 2/25/2025 9:57:02 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Order_Payments](
	[payment_id] [int] IDENTITY(1,1) NOT NULL,
	[user_id] [int] NOT NULL,
	[order_id] [int] NOT NULL,
	[amount] [decimal](10, 2) NOT NULL,
	[payment_method] [nvarchar](20) NOT NULL,
	[status] [nvarchar](20) NULL,
	[created_at] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[payment_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Orders]    Script Date: 2/25/2025 9:57:02 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Orders](
	[order_id] [int] IDENTITY(1,1) NOT NULL,
	[user_id] [int] NOT NULL,
	[total_price] [decimal](10, 2) NOT NULL,
	[status] [nvarchar](20) NULL,
	[created_at] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[order_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PickleBallField]    Script Date: 2/25/2025 9:57:02 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PickleBallField](
	[IDPickleBallField] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NULL,
	[TypeofFootballField] [int] NULL,
	[Price] [money] NULL,
	[Image] [nvarchar](100) NULL,
	[Status] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[IDPickleBallField] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PickleBallFieldFeedback]    Script Date: 2/25/2025 9:57:02 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PickleBallFieldFeedback](
	[FeedbackID] [int] IDENTITY(1,1) NOT NULL,
	[UserID] [int] NOT NULL,
	[PickleBallFieldID] [int] NOT NULL,
	[Rating] [int] NOT NULL,
	[Comment] [nvarchar](max) NULL,
	[CreatedAt] [datetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[FeedbackID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PickleBallFieldSchedule]    Script Date: 2/25/2025 9:57:02 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PickleBallFieldSchedule](
	[IDPickleBallFieldSchedule] [int] IDENTITY(1,1) NOT NULL,
	[StartTime] [time](7) NULL,
	[Endtime] [time](7) NULL,
	[IDPickleBallField] [int] NULL,
	[Status] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[IDPickleBallFieldSchedule] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ProductFeedback]    Script Date: 2/25/2025 9:57:02 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ProductFeedback](
	[FeedbackID] [int] IDENTITY(1,1) NOT NULL,
	[UserID] [int] NOT NULL,
	[ProductID] [int] NOT NULL,
	[Rating] [int] NOT NULL,
	[Comment] [nvarchar](max) NULL,
	[CreatedAt] [datetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[FeedbackID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Products]    Script Date: 2/25/2025 9:57:02 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Products](
	[product_id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](255) NOT NULL,
	[description] [nvarchar](max) NULL,
	[price] [decimal](10, 2) NOT NULL,
	[stock_quantity] [int] NOT NULL,
	[created_at] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[product_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[RegisteredPickleBallField]    Script Date: 2/25/2025 9:57:02 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[RegisteredPickleBallField](
	[IDRegisteredPickleBallField] [int] IDENTITY(1,1) NOT NULL,
	[IDAccount1] [int] NULL,
	[IDAccount2] [int] NULL,
	[IDPickleBallFieldSchedule] [int] NULL,
	[Date] [date] NULL,
	[Name] [nvarchar](100) NULL,
	[PhoneNumber] [nvarchar](50) NULL,
	[Deposit] [money] NULL,
	[Status] [int] NULL,
	[Note] [nvarchar](200) NULL,
PRIMARY KEY CLUSTERED 
(
	[IDRegisteredPickleBallField] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Order_Payments] ADD  DEFAULT ('pending') FOR [status]
GO
ALTER TABLE [dbo].[Order_Payments] ADD  DEFAULT (getdate()) FOR [created_at]
GO
ALTER TABLE [dbo].[Orders] ADD  DEFAULT ('pending') FOR [status]
GO
ALTER TABLE [dbo].[Orders] ADD  DEFAULT (getdate()) FOR [created_at]
GO
ALTER TABLE [dbo].[PickleBallFieldFeedback] ADD  DEFAULT (getdate()) FOR [CreatedAt]
GO
ALTER TABLE [dbo].[ProductFeedback] ADD  DEFAULT (getdate()) FOR [CreatedAt]
GO
ALTER TABLE [dbo].[Products] ADD  DEFAULT ((0)) FOR [stock_quantity]
GO
ALTER TABLE [dbo].[Products] ADD  DEFAULT (getdate()) FOR [created_at]
GO
ALTER TABLE [dbo].[Bill]  WITH CHECK ADD FOREIGN KEY([IDAccount2])
REFERENCES [dbo].[Account] ([IDAccount])
ON DELETE SET NULL
GO
ALTER TABLE [dbo].[Bill]  WITH CHECK ADD FOREIGN KEY([IDRegisteredPickleBallField])
REFERENCES [dbo].[RegisteredPickleBallField] ([IDRegisteredPickleBallField])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Order_Details]  WITH CHECK ADD FOREIGN KEY([order_id])
REFERENCES [dbo].[Orders] ([order_id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Order_Details]  WITH CHECK ADD FOREIGN KEY([product_id])
REFERENCES [dbo].[Products] ([product_id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Order_Payments]  WITH CHECK ADD FOREIGN KEY([order_id])
REFERENCES [dbo].[Orders] ([order_id])
GO
ALTER TABLE [dbo].[Order_Payments]  WITH CHECK ADD FOREIGN KEY([user_id])
REFERENCES [dbo].[Account] ([IDAccount])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD FOREIGN KEY([user_id])
REFERENCES [dbo].[Account] ([IDAccount])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[PickleBallFieldFeedback]  WITH CHECK ADD  CONSTRAINT [FK_PickleBallFieldFeedback_Account] FOREIGN KEY([UserID])
REFERENCES [dbo].[Account] ([IDAccount])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[PickleBallFieldFeedback] CHECK CONSTRAINT [FK_PickleBallFieldFeedback_Account]
GO
ALTER TABLE [dbo].[PickleBallFieldFeedback]  WITH CHECK ADD  CONSTRAINT [FK_PickleBallFieldFeedback_PickleBallField] FOREIGN KEY([PickleBallFieldID])
REFERENCES [dbo].[PickleBallField] ([IDPickleBallField])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[PickleBallFieldFeedback] CHECK CONSTRAINT [FK_PickleBallFieldFeedback_PickleBallField]
GO
ALTER TABLE [dbo].[PickleBallFieldSchedule]  WITH CHECK ADD FOREIGN KEY([IDPickleBallField])
REFERENCES [dbo].[PickleBallField] ([IDPickleBallField])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[ProductFeedback]  WITH CHECK ADD  CONSTRAINT [FK_ProductFeedback_Account] FOREIGN KEY([UserID])
REFERENCES [dbo].[Account] ([IDAccount])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[ProductFeedback] CHECK CONSTRAINT [FK_ProductFeedback_Account]
GO
ALTER TABLE [dbo].[ProductFeedback]  WITH CHECK ADD  CONSTRAINT [FK_ProductFeedback_Products] FOREIGN KEY([ProductID])
REFERENCES [dbo].[Products] ([product_id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[ProductFeedback] CHECK CONSTRAINT [FK_ProductFeedback_Products]
GO
ALTER TABLE [dbo].[RegisteredPickleBallField]  WITH CHECK ADD FOREIGN KEY([IDAccount1])
REFERENCES [dbo].[Account] ([IDAccount])
GO
ALTER TABLE [dbo].[RegisteredPickleBallField]  WITH CHECK ADD FOREIGN KEY([IDAccount2])
REFERENCES [dbo].[Account] ([IDAccount])
ON DELETE SET NULL
GO
ALTER TABLE [dbo].[RegisteredPickleBallField]  WITH CHECK ADD FOREIGN KEY([IDPickleBallFieldSchedule])
REFERENCES [dbo].[PickleBallFieldSchedule] ([IDPickleBallFieldSchedule])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Order_Payments]  WITH CHECK ADD CHECK  (([payment_method]='bank_transfer' OR [payment_method]='paypal' OR [payment_method]='credit_card'))
GO
ALTER TABLE [dbo].[Order_Payments]  WITH CHECK ADD CHECK  (([status]='failed' OR [status]='completed' OR [status]='pending'))
GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD CHECK  (([status]='cancelled' OR [status]='completed' OR [status]='pending'))
GO
USE [master]
GO
ALTER DATABASE [PickleBall48] SET  READ_WRITE 
GO
