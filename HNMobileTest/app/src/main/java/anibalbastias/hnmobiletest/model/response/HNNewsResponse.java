package anibalbastias.hnmobiletest.model.response;

import java.util.ArrayList;

/**
 * Created by anibalbastias on 16-03-16.
 */
public class HNNewsResponse {
    ArrayList<Hits> hits;
    int nbHits;
    int page;
    int nbPages;
    int hitsPerPage;
    int processingTimeMS;
    String query;
    String params;

    public class Hits {
        String created_at;
        String title;
        String url;
        String author;
        int points;
        String story_text;
        String comment_text;
        int num_comments;
        String story_id;
        String story_title;
        String story_url;
        String parent_id;
        int created_at_i;
        ArrayList<String> _tags;
        String objectID;
        _HighlightResult _highlightResult;

        public class _HighlightResult {
            Title title;
            Url url;
            Author author;

            public class Title {
                String value;
                String matchLevel;
                ArrayList<String> matchedWord;

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                public String getMatchLevel() {
                    return matchLevel;
                }

                public void setMatchLevel(String matchLevel) {
                    this.matchLevel = matchLevel;
                }

                public ArrayList<String> getMatchedWord() {
                    return matchedWord;
                }

                public void setMatchedWord(ArrayList<String> matchedWord) {
                    this.matchedWord = matchedWord;
                }
            }

            public class Url {
                String value;
                String matchLevel;
                ArrayList<String> matchedWord;

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                public String getMatchLevel() {
                    return matchLevel;
                }

                public void setMatchLevel(String matchLevel) {
                    this.matchLevel = matchLevel;
                }

                public ArrayList<String> getMatchedWord() {
                    return matchedWord;
                }

                public void setMatchedWord(ArrayList<String> matchedWord) {
                    this.matchedWord = matchedWord;
                }
            }

            public class Author {
                String value;
                String matchLevel;
                ArrayList<String> matchedWord;

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                public String getMatchLevel() {
                    return matchLevel;
                }

                public void setMatchLevel(String matchLevel) {
                    this.matchLevel = matchLevel;
                }

                public ArrayList<String> getMatchedWord() {
                    return matchedWord;
                }

                public void setMatchedWord(ArrayList<String> matchedWord) {
                    this.matchedWord = matchedWord;
                }
            }

            public Title getTitle() {
                return title;
            }

            public void setTitle(Title title) {
                this.title = title;
            }

            public Url getUrl() {
                return url;
            }

            public void setUrl(Url url) {
                this.url = url;
            }

            public Author getAuthor() {
                return author;
            }

            public void setAuthor(Author author) {
                this.author = author;
            }
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public int getPoints() {
            return points;
        }

        public void setPoints(int points) {
            this.points = points;
        }

        public String getStory_text() {
            return story_text;
        }

        public void setStory_text(String story_text) {
            this.story_text = story_text;
        }

        public String getComment_text() {
            return comment_text;
        }

        public void setComment_text(String comment_text) {
            this.comment_text = comment_text;
        }

        public int getNum_comments() {
            return num_comments;
        }

        public void setNum_comments(int num_comments) {
            this.num_comments = num_comments;
        }

        public String getStory_id() {
            return story_id;
        }

        public void setStory_id(String story_id) {
            this.story_id = story_id;
        }

        public String getStory_title() {
            return story_title;
        }

        public void setStory_title(String story_title) {
            this.story_title = story_title;
        }

        public String getStory_url() {
            return story_url;
        }

        public void setStory_url(String story_url) {
            this.story_url = story_url;
        }

        public String getParent_id() {
            return parent_id;
        }

        public void setParent_id(String parent_id) {
            this.parent_id = parent_id;
        }

        public int getCreated_at_i() {
            return created_at_i;
        }

        public void setCreated_at_i(int created_at_i) {
            this.created_at_i = created_at_i;
        }

        public ArrayList<String> get_tags() {
            return _tags;
        }

        public void set_tags(ArrayList<String> _tags) {
            this._tags = _tags;
        }

        public String getObjectID() {
            return objectID;
        }

        public void setObjectID(String objectID) {
            this.objectID = objectID;
        }

        public _HighlightResult get_highlightResult() {
            return _highlightResult;
        }

        public void set_highlightResult(_HighlightResult _highlightResult) {
            this._highlightResult = _highlightResult;
        }
    }

    public ArrayList<Hits> getHits() {
        return hits;
    }

    public void setHits(ArrayList<Hits> hits) {
        this.hits = hits;
    }

    public int getNbHits() {
        return nbHits;
    }

    public void setNbHits(int nbHits) {
        this.nbHits = nbHits;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getNbPages() {
        return nbPages;
    }

    public void setNbPages(int nbPages) {
        this.nbPages = nbPages;
    }

    public int getHitsPerPage() {
        return hitsPerPage;
    }

    public void setHitsPerPage(int hitsPerPage) {
        this.hitsPerPage = hitsPerPage;
    }

    public int getProcessingTimeMS() {
        return processingTimeMS;
    }

    public void setProcessingTimeMS(int processingTimeMS) {
        this.processingTimeMS = processingTimeMS;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
}
