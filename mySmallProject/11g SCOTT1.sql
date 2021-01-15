
create or replace function find_user (in_id varchar2, in_pwd varchar2) return Number
is
    cursor find_user_cursor is select id, pwd from user_table;
    user_rec user_table%rowtype;
begin
    for user_rec in find_user_cursor loop
        if in_id = user_rec.id and in_pwd = user_rec.pwd
            then return 1;
        end if;
        
    end loop;
end;
/