INSERT INTO employee(name, created_at, updated_at, delete_flag)
    VALUES ("煌木　太郎", CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0);
INSERT INTO employee(name, created_at, updated_at, delete_flag)
    VALUES ("田中　太郎", CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0);
INSERT INTO authentication(code, password, role, employee_id)
    VALUES ("ktaro", "$2a$08$clh9XaYYznpX9WDqySgiCuUu4znpSeu2oJi5l2Q00UJs42Llrbd7S", "管理者", 1);
INSERT INTO authentication(code, password, role, employee_id)
    VALUES ("ttaro", "$2a$10$F1k.2HZtkRpoSDymdZCTnuI7eVdoKP.Yb8gtiWmVTKejp53Htlm56", "一般", 2);

INSERT INTO report(report_date, title, content, created_at, updated_at, employee_id)
    VALUES (CURRENT_TIMESTAMP, "本日の営業先", "商談内容", CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);
INSERT INTO report(report_date, title, content, created_at, updated_at, employee_id)
    VALUES (CURRENT_TIMESTAMP, "明日の営業先", "業務内容", CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);
INSERT INTO report(report_date, title, content, created_at, updated_at, employee_id)
    VALUES (CURRENT_TIMESTAMP, "本日の営業先", "商談内容", CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2);
INSERT INTO report(report_date, title, content, created_at, updated_at, employee_id)
    VALUES (CURRENT_TIMESTAMP, "明日の営業先", "会議内容", CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2);