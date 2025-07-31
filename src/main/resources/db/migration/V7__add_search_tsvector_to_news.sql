-- Add a new column to store the searchable document
ALTER TABLE news ADD COLUMN document_vector tsvector;

-- Populate the column for existing news by combining title and content
UPDATE news SET document_vector = to_tsvector('english', title || ' ' || content);

-- Create a GIN index for fast searching
CREATE INDEX document_vector_idx ON news USING GIN(document_vector);

-- (Best Practice) Create a trigger to automatically keep the vector updated
CREATE OR REPLACE FUNCTION news_tsvector_update() RETURNS TRIGGER AS $$
BEGIN
    NEW.document_vector := to_tsvector('english', NEW.title || ' ' || NEW.content);
    RETURN NEW;
END
$$ LANGUAGE plpgsql;

CREATE TRIGGER tsvectorupdate BEFORE INSERT OR UPDATE
ON news FOR EACH ROW EXECUTE FUNCTION news_tsvector_update();