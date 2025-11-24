-- # Technology Topics
-------------------
curl -X POST http://localhost:8080/api/topics \
  -H "Content-Type: application/json" \
  -d '{"name": "Technology", "description": "Latest tech news and updates"}'

curl -X POST http://localhost:8080/api/topics \
  -H "Content-Type: application/json" \
  -d '{"name": "Programming", "description": "Programming languages and frameworks"}'

curl -X POST http://localhost:8080/api/topics \
  -H "Content-Type: application/json" \
  -d '{"name": "AI & Machine Learning", "description": "Artificial Intelligence and ML trends"}'

curl -X POST http://localhost:8080/api/topics \
  -H "Content-Type: application/json" \
  -d '{"name": "Web Development", "description": "Frontend and backend web development"}'

curl -X POST http://localhost:8080/api/topics \
  -H "Content-Type: application/json" \
  -d '{"name": "Mobile Development", "description": "iOS and Android development"}'

# Business & Finance
curl -X POST http://localhost:8080/api/topics \
  -H "Content-Type: application/json" \
  -d '{"name": "Business", "description": "Business news and insights"}'

curl -X POST http://localhost:8080/api/topics \
  -H "Content-Type: application/json" \
  -d '{"name": "Finance", "description": "Financial markets and investment"}'

# Lifestyle
curl -X POST http://localhost:8080/api/topics \
  -H "Content-Type: application/json" \
  -d '{"name": "Health & Fitness", "description": "Health tips and fitness advice"}'

curl -X POST http://localhost:8080/api/topics \
  -H "Content-Type: application/json" \
  -d '{"name": "Travel", "description": "Travel guides and destination reviews"}'

curl -X POST http://localhost:8080/api/topics \
  -H "Content-Type: application/json" \
  -d '{"name": "Food & Cooking", "description": "Recipes and culinary tips"}'

curl -X POST http://localhost:8080/api/topics \
  -H "Content-Type: application/json" \
  -d '{"name": "Sports", "description": "Sports news and updates"}'

curl -X POST http://localhost:8080/api/topics \
  -H "Content-Type: application/json" \
  -d '{"name": "Entertainment", "description": "Movies, TV shows, and celebrity news"}'


--  Create Subscribers with Topic Subscriptions
--------------------------------------------------------------
curl -X POST http://localhost:8080/api/subscribers \
  -H "Content-Type: application/json" \
  -d '{"email": "alice.tech@yopmail.com", "topicIds": [1, 2, 3]}'


curl -X POST http://localhost:8080/api/subscribers \
  -H "Content-Type: application/json" \
  -d '{"email": "bob.developer@yopmail.com", "topicIds": [2, 4, 5]}'


curl -X POST http://localhost:8080/api/subscribers \
  -H "Content-Type: application/json" \
  -d '{"email": "charlie.ai@yopmail.com", "topicIds": [3]}'

curl -X POST http://localhost:8080/api/subscribers \
  -H "Content-Type: application/json" \
  -d '{"email": "diana.webdev@yopmail.com", "topicIds": [4]}'

curl -X POST http://localhost:8080/api/subscribers \
  -H "Content-Type: application/json" \
  -d '{"email": "fiona.business@yopmail.com", "topicIds": [6, 7]}'

curl -X POST http://localhost:8080/api/subscribers \
  -H "Content-Type: application/json" \
  -d '{"email": "george.investor@yopmail.com", "topicIds": [7]}'

curl -X POST http://localhost:8080/api/subscribers \
  -H "Content-Type: application/json" \
  -d '{"email": "helen.fitness@yopmail.com", "topicIds": [8]}'

curl -X POST http://localhost:8080/api/subscribers \
  -H "Content-Type: application/json" \
  -d '{"email": "ivan.traveler@yopmail.com", "topicIds": [9]}'

curl -X POST http://localhost:8080/api/subscribers \
  -H "Content-Type: application/json" \
  -d '{"email": "julia.chef@yopmail.com", "topicIds": [10]}'

curl -X POST http://localhost:8080/api/subscribers \
  -H "Content-Type: application/json" \
  -d '{"email": "kevin.sports@yopmail.com", "topicIds": [11]}'

curl -X POST http://localhost:8080/api/subscribers \
  -H "Content-Type: application/json" \
  -d '{"email": "lisa.entertainment@yopmail.com", "topicIds": [12]}'

# Multi-topic subscribers
curl -X POST http://localhost:8080/api/subscribers \
  -H "Content-Type: application/json" \
  -d '{"email": "mike.general@yopmail.com", "topicIds": [1, 6, 8, 11]}'

curl -X POST http://localhost:8080/api/subscribers \
  -H "Content-Type: application/json" \
  -d '{"email": "nina.all@yopmail.com", "topicIds": [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]}'

-- Create 10+ Content Items (Scheduled at Different Times)
--------------------------------------------------------------
# Technology content
curl -X POST http://localhost:8080/api/content \
  -H "Content-Type: application/json" \
  -d '{
    "topicId": 1,
    "subject": "Weekly Technology Roundup - December 2024",
    "body": "<h1>Tech News This Week</h1><p>Here are the latest developments in technology...</p><ul><li>New AI breakthroughs</li><li>Latest smartphone releases</li><li>Cloud computing updates</li></ul>",
    "scheduledAt": "2025-11-24T10:00:00Z"
  }'

 curl -X POST http://localhost:8080/api/content \
  -H "Content-Type: application/json" \
  -d '{
    "topicId": 2,
    "subject": "Programming Tips: Best Practices for 2024",
    "body": "<h1>Programming Best Practices</h1><p>Learn the latest programming best practices...</p><h2>Key Points:</h2><ol><li>Code review guidelines</li><li>Testing strategies</li><li>Performance optimization</li></ol>",
    "scheduledAt": "2025-11-24T08:32:00Z"
  }'

 curl -X POST http://localhost:8080/api/content \
  -H "Content-Type: application/json" \
  -d '{
    "topicId": 3,
    "subject": "AI & ML Newsletter: Latest Research Papers",
    "body": "<h1>AI Research Update</h1><p>This week in AI research...</p><p>New papers published on transformer architectures and reinforcement learning.</p>",
    "scheduledAt": "2025-11-24T08:15:00Z"
  }'

curl -X POST http://localhost:8080/api/content \
  -H "Content-Type: application/json" \
  -d '{
    "topicId": 4,
    "subject": "Web Development Trends: React vs Vue vs Angular",
    "body": "<h1>Framework Comparison</h1><p>A comprehensive comparison of popular web frameworks...</p><table><tr><th>Framework</th><th>Pros</th><th>Cons</th></tr><tr><td>React</td><td>Large ecosystem</td><td>Steep learning curve</td></tr></table>",
    "scheduledAt": "2025-11-24T10:00:00Z"
  }'

curl -X POST http://localhost:8080/api/content \
  -H "Content-Type: application/json" \
  -d '{
    "topicId": 5,
    "subject": "Mobile App Development: iOS 18 Features",
    "body": "<h1>iOS 18 Updates</h1><p>Discover the new features in iOS 18...</p><ul><li>Enhanced privacy controls</li><li>New widgets</li><li>Improved Siri</li></ul>",
    "scheduledAt": "2025-11-24T10:00:00Z"
  }'

# Business & Finance content
curl -X POST http://localhost:8080/api/content \
  -H "Content-Type: application/json" \
  -d '{
    "topicId": 6,
    "subject": "Business Weekly: Market Insights",
    "body": "<h1>Business News</h1><p>This week in business...</p><p>Market trends, startup funding, and corporate news.</p>",
    "scheduledAt": "2025-11-24T10:00:00Z"
  }'

curl -X POST http://localhost:8080/api/content \
  -H "Content-Type: application/json" \
  -d '{
    "topicId": 7,
    "subject": "Finance Update: Stock Market Analysis",
    "body": "<h1>Market Analysis</h1><p>Weekly stock market analysis...</p><p>Key indices performance and investment recommendations.</p>",
    "scheduledAt": "2025-11-24T10:00:00Z"
  }'

# Lifestyle content
curl -X POST http://localhost:8080/api/content \
  -H "Content-Type: application/json" \
  -d '{
    "topicId": 8,
    "subject": "Health & Fitness: New Year Resolution Tips",
    "body": "<h1>Fitness Guide</h1><p>Start your fitness journey with these tips...</p><h2>Workout Plan:</h2><ol><li>Cardio 3x per week</li><li>Strength training 2x per week</li><li>Rest and recovery</li></ol>",
    "scheduledAt": "2025-11-24T10:00:00Z"
  }'

curl -X POST http://localhost:8080/api/content \
  -H "Content-Type: application/json" \
  -d '{
    "topicId": 9,
    "subject": "Travel Guide: Top Destinations for 2025",
    "body": "<h1>Travel Destinations</h1><p>Plan your next adventure...</p><p>Discover the most beautiful places to visit in 2025.</p><ul><li>Bali, Indonesia</li><li>Santorini, Greece</li><li>Kyoto, Japan</li></ul>",
    "scheduledAt": "2025-11-24T10:00:00Z"
  }'

curl -X POST http://localhost:8080/api/content \
  -H "Content-Type: application/json" \
  -d '{
    "topicId": 10,
    "subject": "Food & Cooking: Holiday Recipe Collection",
    "body": "<h1>Holiday Recipes</h1><p>Delicious recipes for the holiday season...</p><h2>Featured Recipe: Roast Turkey</h2><p>Ingredients and step-by-step instructions...</p>",
    "scheduledAt": "2025-11-24T10:00:00Z"
  }'

curl -X POST http://localhost:8080/api/content \
  -H "Content-Type: application/json" \
  -d '{
    "topicId": 11,
    "subject": "Sports Update: Championship Highlights",
    "body": "<h1>Sports News</h1><p>Latest from the world of sports...</p><p>Championship results, player transfers, and upcoming matches.</p>",
    "scheduledAt": "2025-11-24T10:00:00Z"
  }'

curl -X POST http://localhost:8080/api/content \
  -H "Content-Type: application/json" \
  -d '{
    "topicId": 12,
    "subject": "Entertainment Weekly: Movie & TV Reviews",
    "body": "<h1>Entertainment News</h1><p>This week in entertainment...</p><p>Movie reviews, TV show updates, and celebrity news.</p>",
    "scheduledAt": "2025-11-24T10:00:00Z"
  }'

# Additional content for same topics (to test multiple emails)
curl -X POST http://localhost:8080/api/content \
  -H "Content-Type: application/json" \
  -d '{
    "topicId": 1,
    "subject": "Technology: Cloud Computing Revolution",
    "body": "<h1>Cloud Computing</h1><p>How cloud computing is transforming businesses...</p>",
    "scheduledAt": "2025-11-24T10:00:00Z"
  }'

curl -X POST http://localhost:8080/api/content \
  -H "Content-Type: application/json" \
  -d '{
    "topicId": 2,
    "subject": "Programming: Debugging Techniques",
    "body": "<h1>Debugging Guide</h1><p>Master the art of debugging...</p>",
    "scheduledAt": "2025-11-24T08:00:00Z"
  }'