\c assets;
DO
$$
    DECLARE
        buildingid buildings.id%TYPE;
        storeyid   storeys.id%TYPE;
    BEGIN
        INSERT INTO buildings (name, address)
        VALUES ('oriente', 'ekzemplo vojo 1')
        RETURNING id INTO buildingid;
        INSERT INTO storeys (name, building_id)
        VALUES ('1. supre', buildingid)
        RETURNING id INTO storeyid;
        INSERT INTO rooms (name, storey_id)
        VALUES ('101', storeyid),
               ('102', storeyid),
               ('103', storeyid);
        INSERT INTO storeys (name, building_id)
        VALUES ('2. supre', buildingid)
        RETURNING id INTO storeyid;
        INSERT INTO rooms (name, storey_id)
        VALUES ('200', storeyid),
               ('210', storeyid);
        INSERT INTO storeys (name, building_id)
        VALUES ('3. supre', buildingid)
        RETURNING id INTO storeyid;


        INSERT INTO buildings (name, address)
        VALUES ('sude', 'ŝablono strato 42')
        RETURNING id INTO buildingid;
        INSERT INTO storeys (name, building_id)
        VALUES ('teretaĝo', buildingid)
        RETURNING id INTO storeyid;
        INSERT INTO rooms (name, storey_id)
        VALUES ('001', storeyid),
               ('002', storeyid),
               ('003', storeyid);
        INSERT INTO storeys (name, building_id)
        VALUES ('1. supre', buildingid)
        RETURNING id INTO storeyid;
        INSERT INTO rooms (name, storey_id)
        VALUES ('101', storeyid),
               ('102', storeyid),
               ('103', storeyid),
               ('111', storeyid),
               ('112', storeyid);
        INSERT INTO storeys (name, building_id)
        VALUES ('1. supre', buildingid)
        RETURNING id INTO storeyid;
        INSERT INTO rooms (name, storey_id)
        VALUES ('200', storeyid);
    END
$$ LANGUAGE plpgsql;
