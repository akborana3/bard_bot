import telebot
import chatgpt

# Create a Telegram bot client.
bot = telebot.TeleBot('1612851329:AAHrHzGcJ_X0zeVilazgMrlDoECI_qyBJJI')

# Create a ChatGPT API client.
chatgpt = chatgpt.ChatGPTClient('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')

# Define a function to handle incoming messages.
def handle_message(message):
  # Get the user's message.
  user_message = message.text

  # Generate a response using the ChatGPT API.
  chatgpt_response = chatgpt.generate_text(prompt=user_message)

  # Send the response to the user.
  bot.send_message(message.chat.id, chatgpt_response)

# Start the Telegram bot.
bot.polling()
