INSERT INTO public.users
(id, name, last_name)
VALUES(1, 'sveta', 'svetikova');

INSERT INTO public.users
(id, name, last_name)
VALUES(2, 'petr', 'petrov');


INSERT INTO public.tasks
(id, name, description, user_id, started_at, finished_at, status)
VALUES(1, 'taska1', 'redo', 2, '2025-03-19 15:42:02.180 +0300', '2025-04-01 17:55:25.331 +0300', 'COMPLETED');

INSERT INTO public.tasks
(id, name, description, user_id, started_at, finished_at, status)
VALUES(2, 'taska2', 'reuse', 2, '2025-04-02 15:42:02.180 +0300', '2025-04-07 17:55:25.331 +0300', 'COMPLETED');
