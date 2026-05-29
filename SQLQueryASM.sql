-- Tạo cơ sở dữ liệu
CREATE DATABASE NewsPortal;
USE NewsPortal;

-- Bảng loại tin
CREATE TABLE Categories (
    Id VARCHAR(50) PRIMARY KEY,
    Name NVARCHAR(100) NOT NULL
);

-- Bảng người dùng
CREATE TABLE Users (
    Id VARCHAR(50) PRIMARY KEY,
    Password NVARCHAR(255) NOT NULL,
    Fullname NVARCHAR(100),
    Birthday DATE,
    Gender BIT,
    Mobile NVARCHAR(20),
    Email NVARCHAR(100),
    Role BIT 
);

-- Bảng bản tin
CREATE TABLE News (
    Id VARCHAR(50) PRIMARY KEY,
    Title NVARCHAR(255) NOT NULL,
    Content NVARCHAR(MAX),
    Image NVARCHAR(255),
    PostedDate DATE,
    Author VARCHAR(50),
    ViewCount INT DEFAULT 0,
    CategoryId VARCHAR(50),
    Home BIT DEFAULT 0,
    FOREIGN KEY (CategoryId) REFERENCES Categories(Id),
    FOREIGN KEY (Author) REFERENCES Users(Id)
);

-- Bảng email đăng ký nhận tin
CREATE TABLE Newsletters (
   Email NVARCHAR(100) PRIMARY KEY,
    Enabled BIT DEFAULT 1 
);

INSERT INTO Categories(Id, Name) VALUES
('IT', 'Công nghệ thông tin'),
('EDU', 'Giáo dục'),
('SPORT', 'Thể thao'),
('POL', 'Chính trị'),
('ENT', 'Giải trí');

INSERT INTO Users(Id, Password, Fullname, Birthday, Gender, Mobile, Email, Role) VALUES
('admin', '123', 'Nguyễn Văn Admin', '1985-01-01', 1, '0901234567', 'admin@example.com', 1),
('reporter1', '123', 'Trần Thị Phóng Viên', '1990-05-10', 0, '0912345678', 'reporter1@example.com', 0),
('reporter2', '123', 'Lê Văn Nhà Báo', '1992-07-20', 1, '0923456789', 'reporter2@example.com', 0);

INSERT INTO News(Id, Title, Content, Image, PostedDate, Author, ViewCount, CategoryId, Home) VALUES
('N001', 'AI đang thay đổi thế giới', 'Nội dung bài viết về AI...', 'moshehar-shoe-3552588.jpg', '2026-05-25', 'reporter1', 120, 'IT', 1),
('N002', 'Giáo dục trực tuyến bùng nổ', 'Nội dung bài viết về giáo dục...', 'publicdomainpictures-city-69430_1920.jpg', '2026-05-26', 'reporter2', 80, 'EDU', 1),
('N003', 'Đội tuyển Việt Nam thắng lớn', 'Nội dung bài viết về thể thao...', 'engin_akyurt-coffee-2354880.jpg', '2026-05-27', 'reporter1', 200, 'SPORT', 1),
('N004', 'Tin tức chính trị mới nhất', 'Nội dung bài viết về chính trị...', 'artsybee-background-1770152.jpg', '2026-05-28', 'reporter2', 50, 'POL', 0),
('N005', 'Showbiz Việt Nam nóng hổi', 'Nội dung bài viết về giải trí...', 'biancavandijk-coffee-6052420.jpg', '2026-05-29', 'reporter1', 150, 'ENT', 1);

INSERT INTO Newsletters(Email, Enabled) VALUES
('user1@example.com', 1),
('user2@example.com', 1),
('user3@example.com', 0);
