SELECT c.name, COUNT(p.id) AS project_count
FROM client c
         JOIN project p ON c.id = p.client_id
GROUP BY c.name
ORDER BY project_count DESC
    LIMIT 1;
